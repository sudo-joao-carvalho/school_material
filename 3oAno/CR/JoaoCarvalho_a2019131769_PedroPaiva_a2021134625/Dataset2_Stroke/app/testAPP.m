function testAPP(app)
    % Carregar o conjunto de dados TESTE
    test_data = readtable(app.escolheDataset_2.Text);

    % Preparar variáveis de entrada e target
    test_inputs = table2array(test_data(:, 2:end-1))';
    test_targets = full(ind2vec(test_data.stroke' + 1));

    % Carregar as melhores redes neurais gravadas
    path = strcat('../testes/redes/', app.escolheRede.Text);
    load(path); % Carregar a primeira melhor rede neural

    % Simular a rede neural
    if contains(app.escolheRede.Text, 'Teste', 'IgnoreCase', true)
        out = sim(melhorRede_T.net, test_inputs);
        temp_net = melhorRede_T.net;
        temp_tr = melhorRede_T.tr;
    elseif contains(app.escolheRede.Text, 'Global', 'IgnoreCase', true)
        out = sim(melhorRede_G.net, test_inputs);
        temp_net = melhorRede_G.net;
        temp_tr = melhorRede_G.tr;
    end

    % Calcular a precisão
    r=0;
    for i=1:size(out,2)               % Para cada classificacao  
      [a b] = max(out(:,i));          %b guarda a linha onde encontrou valor mais alto da saida obtida
      [c d] = max(test_targets(:,i));  %d guarda a linha onde encontrou valor mais alto da saida desejada
      if b == d                       % se estao na mesma linha, a classificacao foi correta (incrementa 1)
          r = r+1;
      end
    end
    precisao = r/size(out,2)*100;

    if (app.RedeCheckBox_2.Value) == 1
        view(temp_net);
    end

    if (app.MatrizdeConfusoCheckBox_2.Value) == 1
        plotconfusion(test_targets, out);
    end

    if (app.GrficocomoDesempenhoCheckBox_2.Value) == 1
        plotperf(temp_tr);
    end

    % Exibir resultados
    fprintf('Rede [ %s ] usada no dataset [ %s ]\n', app.escolheRede.Text, app.escolheDataset_2.Text);
    fprintf('Precisão: %.2f%%\n', precisao);
end