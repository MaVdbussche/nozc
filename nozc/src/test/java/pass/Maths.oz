declare
B={NewCell _}
D={NewCell _}
E={NewCell _}
Maximum={NewCell _}
A={NewCell 0}
C={Sin @B}
in
B := 90
E := @A
D := 1.2
if ((({IsFloat @D} andthen {IsFloat @E}) orelse ({IsInt @D} andthen {IsInt @E}))) then
Maximum := {Max @D @E}
{Max @D @E Maximum}

end
