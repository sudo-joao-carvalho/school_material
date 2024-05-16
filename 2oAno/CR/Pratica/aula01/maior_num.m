function maior_num ()
    num = input('Introduza um valor (0 para terminar): ');
    maior = num;

    while num ~= 0
        if num > maior
            maior = num;
        end
        num = input('Introduza um valor (0 para terminar): ');
    end

    disp(['O maior número introduzido foi: ', sprintf('%.d', maior)])

end
