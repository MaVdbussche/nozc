declare
fun{Acc L N}
case L of
   L1|nil andthen true then N + L1
[] L1|L2 then local
A=0
in
{Acc L2 N + L1}
end
else 0
end
end
fun{SumList L}
{Acc L 0}
end
List={NewCell 1|2|5|4|7|nil}
in
{Browse {SumList @List}}
