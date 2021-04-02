declare
fun{Acc L N}
case L of
   L1|nil andthen true then A=0
in
N + L1
[] L1|L2 then {Acc L2 N + L1}
else 0
end
end
fun{SumList L}
{Acc L 0}
end
List={NewCell [1 2 3]}
in
{Browse {SumList @List}}
