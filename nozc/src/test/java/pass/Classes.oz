local
class AnotherObject
end
class Point
attr x : 0
attr y : 0
from anotherObject
meth init(A B )
x := A 
y := B end
meth location(L )
skip
end
meth getX(O )
O = @x end
meth moveVertical(NewX )
x := NewX end
meth moveHorizontal(NewY )
y := NewY end
meth move(NewX NewY )
{self moveVertical(NewX)} 
{self moveHorizontal(NewY)} 
{Browse NewX#NewY} end
meth display()
{Browse 'Point at ('#@x#', '#@y#')'} end
end
in
skip
end