// Copyright 2013 Bill Campbell, Swami Iyer and Bahar Akbal-Delibas

package com.barassolutions.toCleanUp;

import java.lang.reflect.Array;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Hashtable;

/**
 * Class representing data types. Types are not explicit in Oz, but making a complete type analysis
 * in the nozc compiler allows writing better error messages to the user, even though the information
 * will mostly be lost upon writing the Oz output file.
 *
 * IdentifierTypes must be "resolved" at some point, so that all Types having
 * the same name refer to the same Type object. resolve() does this.
 */
public class Type {

    /** The Type's internal (Java) representation. * */
    private Class<?> classRep;

    /** Maps type names to their Type representations. */
    private static final Hashtable<String, Type> types = new Hashtable<String, Type>();

    /** The primitive type, int. */
    public final static Type INT = typeFor(int.class);

    /** The primitive type, char. */
    public final static Type CHAR = typeFor(char.class);

    /** The primitive type, boolean. */
    public final static Type BOOLEAN = typeFor(boolean.class);

    /** The primitive type, float. */
    public final static Type FLOAT = typeFor(float.class);

    //TODO others ?

    /** The "any" type (denotes wild expressions). */
    public final static Type ANY = new Type(null);

    /**
     * Construct a Type representation for a type from its Java (Class)
     * representation. Use typeFor() -- that maps types having like classReps to
     * like Types.
     *
     * @param classRep
     *            the Java representation.
     */
    private Type(Class<?> classRep) {
        this.classRep = classRep;
    }

    /** This constructor is to keep the compiler happy. */
    protected Type() {
        super();
    }

    /**
     * Construct a Type representation for a type from its (Java) Class
     * representation. Make sure there is a unique Type for each unique type.
     *
     * @param classRep
     *            the Java representation.
     */
    public static Type typeFor(Class<?> classRep) {
        if (types.get(descriptorFor(classRep)) == null) {
            types.put(descriptorFor(classRep), new Type(classRep));
        }
        return types.get(descriptorFor(classRep));
    }

    /**
     * Return the class representation for a type, appropriate for dealing with
     * the Java reflection API.
     *
     * @return the Class representation for this type.
     */
    public Class<?> classRep() {
        return classRep;
    }

    /**
     * This setter is used by CompilationBlock.preAnalyze() to set the classRep
     * to the specified partial class, computed during pre-analysis.
     *
     * @param classRep
     *            the partial class.
     */
    public void setClassRep(Class<?> classRep) {
        this.classRep = classRep;
    }

    /**
     * Type equality is based on the equality of descriptors.
     *
     * @param that
     *            the other Type.
     * @return true iff the two types are equal.
     */
    public boolean equals(Type that) {
        return this.toDescriptor().equals(that.toDescriptor());
    }

    /**
     * Is this an Array type?
     * //TODO investigate how we handle arrays in Oz ?
     * @return true or false.
     */
    public boolean isArray() {
        return classRep.isArray();
    }

    /**
     * An array type's component type. Meaningful only for array types.
     * //TODO investigate how we handle arrays in Oz ?
     * @return the component type.
     */
    public Type componentType() {
        return typeFor(classRep.getComponentType());
    }

    /**
     * Return the Type's super type (or null if there is none). Meaningful only to class Types.
     * //TODO see inheritance
     * @return the super type.
     */
    public Type superClass() {
        return classRep == null || classRep.getSuperclass() == null ? null
                : typeFor(classRep.getSuperclass());
    }

    /**
     * Is this a primitive type?
     *
     * @return true or false.
     */
    public boolean isPrimitive() {
        return classRep.isPrimitive();
    }


    /**
     * Is this a supertype of that?
     * //TODO see inheritance
     * @param that
     *            the candidate subtype.
     * @return true iff this is a supertype of that.
     */
    public boolean isAssignableFrom(Type that) {
        return this.classRep.isAssignableFrom(that.classRep);
    }

    /**
     * An assertion that this type matches one of the specified types. If there
     * is no match, an error message is returned.
     *
     * @param line
     *            the line near which the mismatch occurs.
     * @param expectedTypes
     *            expected types.
     */
    public void mustMatchOneOf(int line, Type... expectedTypes) {
        if (this == Type.ANY)
            return;
        for (Type expectedType : expectedTypes) {
            if (matchesExpected(expectedType)) {
                return;
            }
        }
        AST.compilationBlock.reportSemanticError(line,
                "Type %s doesn't match any of the expected types %s", this,
                Arrays.toString(expectedTypes));
    }

    /**
     * An assertion that this type matches the specified type. If there is no
     * match, an error message is written.
     *
     * @param line
     *            the line near which the mismatch occurs.
     * @param expectedType
     *            type with which to match.
     */
    public void mustMatchExpected(int line, Type expectedType) {
        if (!matchesExpected(expectedType)) {
            AST.compilationBlock.reportSemanticError(line,
                    "Type %s doesn't match type %s", this, expectedType);
        }
    }

    /**
     * Does this type match the expected type ? For now, "matches" means
     * "equals".
     *
     * @param expected
     *            the type that this might match.
     * @return true or false.
     */
    public boolean matchesExpected(Type expected) {
        return this == Type.ANY || expected == Type.ANY
                || (this == Type.NULLTYPE && expected.isReference()) //TODO remove this line ? Dangerous because means Java-like "equals" for objects !
                || this.equals(expected);
    }

    /**
     * Do argument types match? A helper used for finding candidate methods and
     * constructors.
     *
     * @param argTypes1
     *            arguments (classReps) of one method.
     * @param argTypes2
     *            arguments (classReps) of another method.
     * @return true iff all corresponding types of argTypes1 and argTypes2
     *         match.
     */
    public static boolean argTypesMatch(Class<?>[] argTypes1,
                                        Class<?>[] argTypes2) {
        if (argTypes1.length != argTypes2.length) {
            return false;
        }
        for (int i = 0; i < argTypes1.length; i++) {
            if (!Type.descriptorFor(argTypes1[i]).equals(
                    Type.descriptorFor(argTypes2[i]))) {
                return false;
            }
        }
        return true;
    }

    /**
     * Return the simple (unqualified) name for this Type. Eg, String in place
     * of java.lang.String.
     *
     * @return the simple name.
     */
    public String simpleName() {
        return classRep.getSimpleName();
    }

    /**
     * A printable string representation of this type. Eg, int[], java.lang.String.
     *
     * @return the string representation.
     */
    public String toString() {
        return toJava(this.classRep);
    }

    /**
     * The JVM descriptor for this type. Eg, Ljava/lang/String; for
     * java.lang.String, [[Z for boolean[][].
     *
     * @return the descriptor.
     */
    public String toDescriptor() {
        return descriptorFor(classRep);
    }

    /**
     * A helper translating a type's internal representation to its (JVM)
     * descriptor.
     *
     * @param cls
     *            internal representation whose descriptor is required.
     * @return the JVM descriptor.
     */
    //TODO Now THAT is some great, readable code...
    private static String descriptorFor(Class<?> cls) {
        return cls == null ? "V" : cls == void.class ? "V"
                : cls.isArray() ? "[" + descriptorFor(cls.getComponentType())
                : cls.isPrimitive() ? (cls == int.class ? "I"
                : cls == char.class ? "C"
                : cls == boolean.class ? "Z" : "?")
                : "L" + cls.getName().replace('.', '/') + ";";
    }

    /**
     * The JVM representation for this type's name. This is also called the
     * internal form of the name. Eg, java/lang/String for java.lang.String.
     *
     * @return the type's name in internal form.
     */
    public String jvmName() {
        return this.isArray() || this.isPrimitive() ? this.toDescriptor()
                : classRep.getName().replace('.', '/');
    }

    /**
     * Return the Java denotation for the specified type. Eg, int[], java.lang.String.
     *
     * @param classRep
     *            the internal representation of type whose Java denotation is
     *            required.
     * @return the Java denotation.
     */
    private static String toJava(Class classRep) {
        return classRep.isArray() ? toJava(classRep.getComponentType()) + "[]"
                : classRep.getName();
    }

    /**
     * Return the type's package name. Eg, java.lang for java.lang.String.
     *
     * @return the package name.
     */
    public String packageName() {
        String name = toString();
        return name.lastIndexOf('.') == -1 ? "" : name.substring(0, name
                .lastIndexOf('.') - 1);
    }

    /**
     * Find an appropriate method in this type, given a message (method) name
     * and it's argument types. This is pretty easy given our (current)
     * restriction that the types of the actual arguments must exactly match the
     * types of the formal parameters. Returns null if it cannot find one.
     *
     * @param name
     *            the method name.
     * @param argTypes
     *            the argument types.
     * @return Method with given name and argument types, or null.
     */
    public Method methodFor(String name, Type[] argTypes) {
        Class[] classes = new Class[argTypes.length];
        for (int i = 0; i < argTypes.length; i++) {
            classes[i] = argTypes[i].classRep;
        }
        Class cls = classRep;

        // Search this class and all superclasses
        while (cls != null) {
            java.lang.reflect.Method[] methods = cls.getDeclaredMethods();
            for (java.lang.reflect.Method method : methods) {
                if (method.getName().equals(name)
                        && Type.argTypesMatch(classes, method
                        .getParameterTypes())) {
                    return new Method(method);
                }
            }
            cls = cls.getSuperclass();
        }
        return null;
    }

    /**
     * Find an appropriate constructor in this type, given it's argument types.
     * This is pretty easy given our (current) restriction that the types of the
     * actual arguments must exactly match the types of the formal parameters.
     * Returns null if it cannot find one.
     *
     * @param argTypes
     *            the argument types.
     * @return Constructor with the specified argument types, or null.
     */
    public Constructor constructorFor(Type[] argTypes) {
        Class[] classes = new Class[argTypes.length];
        for (int i = 0; i < argTypes.length; i++) {
            classes[i] = argTypes[i].classRep;
        }

        // Search only this class (we don't inherit constructors)
        java.lang.reflect.Constructor[] constructors = classRep
                .getDeclaredConstructors();
        for (java.lang.reflect.Constructor constructor : constructors) {
            if (argTypesMatch(classes, constructor.getParameterTypes())) {
                return new Constructor(constructor);
            }
        }
        return null;
    }

    /**
     * Return the Field having this name.
     *
     * @param name
     *            the name of the field we want.
     * @return the Field or null if it's not there.
     */
    public Field fieldFor(String name) {
        Class<?> cls = classRep;
        while (cls != null) {
            java.lang.reflect.Field[] fields = cls.getDeclaredFields();
            for (java.lang.reflect.Field field : fields) {
                if (field.getName().equals(name)) {
                    return new Field(field);
                }
            }
            cls = cls.getSuperclass();
        }
        return null;
    }

    /**
     * Convert an array of argument types to a string representation of a
     * parenthesized list of the types, eg, (int, boolean, java.lang.String).
     *
     * @param argTypes
     *            the array of argument types.
     * @return the string representation.
     */
    public static String argTypesAsString(Type[] argTypes) {
        if (argTypes.length == 0) {
            return "()";
        } else {
            StringBuilder str = new StringBuilder("(" + argTypes[0].toString());
            for (int i = 1; i < argTypes.length; i++) {
                str.append(",").append(argTypes[i]);
            }
            str.append(")");
            return str.toString();
        }
    }

    /**
     * Check the accessibility of a member from this type (that is, this type is
     * the referencing type).
     *
     * @param line
     *            the line in which the access occurs.
     * @param member
     *            the member being accessed.
     * @return true if access is valid; false otherwise.
     */
    //TODO lots of thinking to do here
    public boolean checkAccess(int line, Member member) {
        if (!checkAccess(line, classRep, member.declaringType().classRep)) {
            return false;
        }

        // Secondly, the member must be either public,
        if (member.isPublic()) {
            return true;
        }
        java.lang.Package p1 = classRep.getPackage();
        java.lang.Package p2 = member.declaringType().classRep.getPackage();
        if ((p1 == null ? "" : p1.getName()).equals((p2 == null ? "" : p2
                .getName()))) {
            return true;
        }
        if (member.isProtected()) {
            if (classRep.getPackage().getName().equals(
                    member.declaringType().classRep.getPackage().getName())
                    || typeFor(member.getClass().getDeclaringClass())
                    .isAssignableFrom(this)) {
                return true;
            } else {
                AST.compilationBlock.reportSemanticError(line,
                        "The protected member, " + member.name()
                                + ", is not accessible.");
                return false;
            }
        }
        if (member.isPrivate()) {
            if (descriptorFor(classRep).equals(
                    descriptorFor(member.member().getDeclaringClass()))) {
                return true;
            } else {
                AST.compilationBlock.reportSemanticError(line,
                        "The private member, " + member.name()
                                + ", is not accessible.");
                return false;
            }
        }

        // Otherwise, the member has default access
        if (packageName().equals(member.declaringType().packageName())) {
            return true;
        } else {
            AST.compilationBlock.reportSemanticError(line, "The member, "
                    + member.name()
                    + ", is not accessible because it's in a different "
                    + "package.");
            return false;
        }
    }

    /**
     * Check the accessibility of a target type (from this type)
     *
     * @param line
     *            line in which the access occurs.
     * @param targetType
     *            the type being accessed.
     * @return true if access is valid; false otherwise.
     */
    public boolean checkAccess(int line, Type targetType) {
        if (targetType.isPrimitive()) {
            return true;
        }
        if (targetType.isArray()) {
            return this.checkAccess(line, targetType.componentType());
        }
        return checkAccess(line, classRep, targetType.classRep);
    }

    /**
     * Check the accessibility of a type.
     *
     * @param line
     *            the line in which the access occurs.
     * @param referencingType
     *            the type attempting the access.
     * @param type
     *            the type that we want to access.
     * @return true if access is valid; false otherwise.
     */
    public static boolean checkAccess(int line, Class referencingType,
                                      Class type) {
        java.lang.Package p1 = referencingType.getPackage();
        java.lang.Package p2 = type.getPackage();
        if (Modifier.isPublic(type.getModifiers())
                || (p1 == null ? "" : p1.getName()).equals((p2 == null ? ""
                : p2.getName()))) {
            return true;
        } else {
            AST.compilationBlock.reportSemanticError(line, "The type, "
                    + type.getCanonicalName() + ", is not accessible from "
                    + referencingType.getCanonicalName());
            return false;
        }
    }

    /**
     * Resolve this type in the given context. Notice that this has meaning only
     * for TypeName and ArrayTypeName, where names are replaced by real types.
     * Names are looked up in the context.
     *
     * @param context
     *            context in which the names are resolved.
     * @return the resolved type.
     */
    public Type resolve(Context context) {
        return this;
    }

    /**
     * A helper for constructing method signatures for reporting unfound methods
     * and constructors.
     *
     * @param name
     *            the message or Type name.
     * @param argTypes
     *            the actual argument types.
     * @return a printable signature.
     */
    public static String signatureFor(String name, Type[] argTypes) {
        StringBuilder signature = new StringBuilder(name + "(");
        if (argTypes.length > 0) {
            signature.append(argTypes[0].toString());
            for (int i = 1; i < argTypes.length; i++) {
                signature.append(",").append(argTypes[i].toString());
            }
        }
        signature.append(")");
        return signature.toString();
    }
}

/**
 * Any reference type that can be denoted as a (possibly qualified) identifier.
 * For now, this includes only class types.
 */
class TypeName extends Type {

    /**
     * The line in which the identifier occurs in the source file.
     */
    private final int line;

    /** The identifier's name. */
    private final String name;

    /**
     * Construct an TypeName given its line number, and string spelling out its
     * fully qualified name.
     *
     * @param line
     *            the line in which the identifier occurs in the source file.
     * @param name
     *            fully qualified name for the identifier.
     */
    public TypeName(int line, String name) {
        this.line = line;
        this.name = name;
    }

    /**
     * Return the line in which the identifier occurs in the source file.
     *
     * @return the line number.
     */
    public int line() {
        return line;
    }

    /**
     * Return the JVM name for this (identifier) type.
     *
     * @return the JVM name.
     */
    public String jvmName() {
        return name.replace('.', '/');
    }

    /**
     * Return the JVM descriptor for this type.
     *
     * @return the descriptor.
     */
    public String toDescriptor() {
        return "L" + jvmName() + ";";
    }

    /**
     * Return the Java representation of this type. Eg, java.lang.String.
     *
     * @return the qualified name.
     */
    public String toString() {
        return name;
    }

    /**
     * Return the simple name for this type. Eg, String for java.lang.String.
     *
     * @return simple name.
     */
    public String simpleName() {
        return name.substring(name.lastIndexOf('.') + 1);
    }

    /**
     * Resolve this type in the given context. Notice that this has meaning only
     * for TypeName and ArrayTypeName, where names are replaced by real types.
     * Names are looked up in the context.
     *
     * @param context
     *            context in which the names are resolved.
     * @return the resolved type.
     */
    public Type resolve(Context context) {
        Type resolvedType = context.lookupType(name);
        if (resolvedType == null) {
            // Try loading a type with the give fullname
            try {
                resolvedType = typeFor(Class.forName(name));
                context.addType(line, resolvedType);
                // context.compilationUnitContext().addEntry(line,
                // resolvedType.toString(),
                // new TypeNameDefn(resolvedType));
            } catch (Exception e) {
                AST.compilationBlock.reportSemanticError(line,
                        "Unable to locate a type named %s", name);
                resolvedType = Type.ANY;
            }
        }
        if (resolvedType != Type.ANY) {
            Type referencingType = ((TypeDecl) (context.classContext.definition())).thisType();
            Type.checkAccess(line, referencingType.classRep(), resolvedType
                    .classRep());
        }
        return resolvedType;
    }
}

/**
 * The (temporary) representation of an array's type. It is built by the Parser
 * to stand in for a Type until analyze(), at which point it is resolved to an
 * actual Type object (having a Class that identifies it).
 */ //TODO see how arrays are handled eventually
class ArrayTypeName extends Type {

    /** The array's base or component type. */
    private Type componentType;

    /**
     * Construct an array's type given its component type.
     *
     * @param componentType
     *            the type of its elements.
     */
    public ArrayTypeName(Type componentType) {
        this.componentType = componentType;
    }

    /**
     * Return the (component) type of its elements.
     *
     * @return the component type.
     */
    public Type componentType() {
        return componentType;
    }

    /**
     * Return the JVM descriptor for this type.
     *
     * @return the descriptor.
     */
    public String toDescriptor() {
        return "[" + componentType.toDescriptor();
    }

    /**
     * A string representation of the type in Java form.
     *
     * @return the representation in Java form.
     */
    public String toString() {
        return componentType.toString() + "[]";
    }

    /**
     * Resolve this type in the given context.
     *
     * @param context
     *            context in which the names are resolved.
     * @return the resolved type.
     */
    public Type resolve(Context context) {
        componentType = componentType.resolve(context);

        // The API forces us to make an instance and get its
        // type.
        Class classRep = Array.newInstance(componentType().classRep(), 0)
                .getClass();
        return Type.typeFor(classRep);
    }
}
