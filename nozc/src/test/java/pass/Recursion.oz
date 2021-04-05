declare
fun{Acc L N}
case L of
   L1|nil andthen (true orelse (false andthen true)) then N + L1
[] L1|L2 then {Acc L2 N + L1}
else 0
end
end
fun{SumList L}
{Acc L 0}
end
List={NewCell 1|2|3|nil}
in
{Browse {SumList @List}}
