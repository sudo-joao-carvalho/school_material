%{
function p = filtra_matriz(m)
    p=[];
    for i=1 : numel(m)
        if mod(m(i),2) == 0
            p = [p m(i)];
        end
    end
end
%}

%sem o for:

function p = filtra_matriz(m)
    indices=find(mod(m,2)==0);
    p=m(indices);
    p = p';
end

%CW:
%pares = filtra_matriz(m)