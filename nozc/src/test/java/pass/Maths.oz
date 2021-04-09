declare
A={NewCell 0}
D={NewCell _}
Maximum={NewCell _}
B={NewCell _}
E={NewCell _}
C={Sin @B}
in
B := 90
E := @A
D := 1.2
if ((({IsFloat @D} andthen {IsFloat @E}) orelse ({IsInt @D} andthen {IsInt @E}))) then
Maximum := {Max @D @E}
{Max @D @E Maximum}

end