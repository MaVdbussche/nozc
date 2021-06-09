declare
Out
Fibo
Show
in
Show = proc{$ S}
{Browse S} 
end 
Fibo = fun{$ N}
if ((N < 2)) then
1
else
({Fibo N - 1}  + {Fibo N - 2} )
end
end 
Out = {Fibo 30}  
{Show Out} 