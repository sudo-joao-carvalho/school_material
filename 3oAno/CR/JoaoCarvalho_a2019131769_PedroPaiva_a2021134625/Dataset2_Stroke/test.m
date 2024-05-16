function test()
    % Carregar o conjunto de dados TESTE
    test_data = readtable('Test.csv');

    % Preparar variáveis de entrada e target
    test_inputs = table2array(test_data(:, 2:end-1))';
    test_targets = full(ind2vec(test_data.stroke' + 1));

    % Nomes das redes
    nomes_redes = {'cfg5_Global', 'cfg10_Global', 'cfg15_Global', 'cfg18_Teste', 'cfg31_Teste', 'cfg32_Teste'};

    % Inicializar a precisão total
    precisao_total = 0;

    % Loop sobre cada rede neural
    for rede = 1:numel(nomes_redes)
        % Carregar a rede neural
        load(['testes/redes/' nomes_redes{rede} '.mat']);
        
        % Simular a rede neural
        if contains(nomes_redes{rede}, 'Teste', 'IgnoreCase', true)
            out = sim(melhorRede_T.net, test_inputs);
        elseif contains(nomes_redes{rede}, 'Global', 'IgnoreCase', true)
            out = sim(melhorRede_G.net, test_inputs);
        end

        % Calcular a precisão
        r = 0;
        for i = 1:size(out,2)               % Para cada classificacao  
            [a, b] = max(out(:,i));          % b guarda a linha onde encontrou valor mais alto da saida obtida
            [c, d] = max(test_targets(:,i));  % d guarda a linha onde encontrou valor mais alto da saida desejada
            if b == d                       % se estão na mesma linha, a classificacao foi correta (incrementa 1)
                r = r + 1;
            end
        end
        precisao_rede = r / size(out,2) * 100;
        
        % Adicionar a precisão desta rede à precisão total
        precisao_total = precisao_total + precisao_rede;
        
        % Exibir precisão da rede atual
        fprintf('Precisão da %s: %.2f%%\n', nomes_redes{rede}, precisao_rede);
    end

    % Calcular a precisão média
    precisao_media = precisao_total / numel(nomes_redes);

    % Exibir precisão média
    fprintf('Precisão Média das 6 Redes: %.2f%%\n', precisao_media);
end
