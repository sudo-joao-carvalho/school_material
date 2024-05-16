function trainAPP(app)
    close all;

    iteracoes = app.sliderIteracoes.Value; % Obtendo o valor do slider de iterações do app
    pathConfig1 = strcat('../testes/redes/', app.nomeMelhorRede.Value, '_Global.mat'); % Obtendo o valor do nome da melhor rede do app
    pathConfig2 = strcat('../testes/redes/', app.nomeMelhorRede.Value, '_Teste.mat');

    % Médias
    media_tempo = 0;
    media_precisao_total = 0;
    media_precisao_teste = 0;
    media_erro = 0;
    
    % Melhor Global
    melhorIndice_G = 0;
    melhorPrecisaoTotal_G = 0;
    melhorPrecisaoTeste_G = 0;
    melhorErro_G = 0;
    melhorTempo_G = 0;

    % Melhor Teste
    melhorIndice_T = 0;
    melhorPrecisaoTotal_T = 0;
    melhorPrecisaoTeste_T = 0;
    melhorErro_T = 0;
    melhorTempo_T = 0;

    if (app.GuardaraMelhorRedeCheckBox.Value == 1)
        melhorRede_G = struct();
        melhorRede_T = struct();
    end

    % Carrega o dataset
    train_data = readtable(app.escolheDataset.Text);

    train_inputs = table2array(train_data(:, 2:end-1))';
    train_targets = full(ind2vec(table2array(train_data(:,end))' + 1));
    
    for k = 1:iteracoes
        % Criar a rede neural
        nNeuronios = app.nNeuronios.Value;
        if strcmp(app.NdeCamadasDropDown.Value, '1 Camada')
            net = feedforwardnet(nNeuronios);
            net.layers{1}.transferFcn = app.fAtivacao1.Value;
            net.layers{2}.transferFcn = app.fAtivacao2.Value;

        elseif strcmp(app.NdeCamadasDropDown.Value, '2 Camadas')
            net = feedforwardnet([nNeuronios, nNeuronios]);
            net.layers{1}.transferFcn = app.fAtivacao1.Value;
            net.layers{2}.transferFcn = app.fAtivacao2.Value;
            net.layers{3}.transferFcn = app.fAtivacao3.Value;

        elseif strcmp(app.NdeCamadasDropDown.Value, '3 Camadas')
            net = feedforwardnet([nNeuronios, nNeuronios, nNeuronios]);
            net.layers{1}.transferFcn = app.fAtivacao1.Value;
            net.layers{2}.transferFcn = app.fAtivacao2.Value;
            net.layers{3}.transferFcn = app.fAtivacao3.Value;
            net.layers{4}.transferFcn = app.fAtivacao4.Value;

        end
        
        % Configuração da rede neural
        
        % Adicione mais camadas conforme necessário

        net.trainFcn = app.fTreino.Value;
        net.trainParam.showWindow = 0;

        net.divideFcn = 'dividerand';
        net.divideParam.trainRatio = app.segTrain.Value;
        net.divideParam.valRatio = app.segValidation.Value;
        net.divideParam.testRatio = app.segTest.Value;

        % Treinar a rede
        tic;
        [net,tr] = train(net, train_inputs, train_targets);
        tempo_execucao = toc;

        % Simulação
        out = sim(net, train_inputs);

        % Calcular a precisão total
        precisao_total = calcularPrecisao(out, train_targets);

        % Simulação no conjunto de teste
        test_inputs = train_inputs(:, tr.testInd);
        test_targets = train_targets(:, tr.testInd);
        out_test = sim(net, test_inputs);

        % Calcular a precisão no conjunto de teste
        precisao_teste = calcularPrecisao(out_test, test_targets);

        % Calcular o erro MSE
        erro_mse = perform(net, test_targets, out_test);

        % Atualizar os melhores valores encontrados
        if precisao_total > melhorPrecisaoTotal_G
            melhorIndice_G = k;
            melhorPrecisaoTotal_G = precisao_total;
            melhorPrecisaoTeste_G = precisao_teste;
            melhorErro_G = erro_mse;
            melhorTempo_G = tempo_execucao;
            
            if (app.GuardaraMelhorRedeCheckBox.Value == 1)
                % Guardar a melhor rede
                melhorRede_G.net = net;
                melhorRede_G.tr = tr;
            end
        end

        if precisao_teste > melhorPrecisaoTeste_T
            melhorIndice_T = k;
            melhorPrecisaoTotal_T = precisao_total;
            melhorPrecisaoTeste_T = precisao_teste;
            melhorErro_T = erro_mse;
            melhorTempo_T = tempo_execucao;
            
            if (app.GuardaraMelhorRedeCheckBox.Value == 1)
                % Guardar a melhor rede
                melhorRede_T.net = net;
                melhorRede_T.tr = tr;
            end
        end

        %Mostrar resultados
        fprintf('------------------------------------\n')
        fprintf('Rede [%d]\n',k);
        fprintf('- Precisão Total: %.2f%%\n', precisao_total);
        fprintf('- Precisão Teste: %.2f%%\n', precisao_teste);
        fprintf('- Tempo de Execução: %.2f segundos\n', tempo_execucao);
        fprintf('- Erro MSE: %.2f\n', erro_mse);
        
        media_precisao_total = media_precisao_total + precisao_total;
        media_precisao_teste = media_precisao_teste + precisao_teste;
        media_tempo = media_tempo + tempo_execucao;
        media_erro = media_erro + erro_mse;
    end
    
    if (app.RedeCheckBox.Value) == 1
        view(net);
    end

    if (app.MatrizdeConfusoCheckBox.Value) == 1
        plotconfusion(train_targets, out);
    end

    if (app.GrficocomoDesempenhoCheckBox.Value) == 1
        plotperf(tr);
    end

    % Calcular médias
    media_precisao_total = media_precisao_total / iteracoes;
    media_precisao_teste = media_precisao_teste / iteracoes;
    media_tempo = media_tempo / iteracoes;
    media_erro = media_erro / iteracoes;
    
    if (app.GuardaraMelhorRedeCheckBox.Value == 1)
        save(pathConfig1,'melhorRede_G');
        save(pathConfig2,'melhorRede_T');
    end

    % Médias
    fprintf('\n------------------------------------\n')
    fprintf('\nMédia das %.0f Iterações:\n',iteracoes);
    fprintf('- Precisão Total Média: %.2f%%\n', media_precisao_total);
    fprintf('- Precisão Teste Média: %.2f%%\n', media_precisao_teste);
    if(app.TempoExecucao.Value) == 1
        fprintf('- Tempo de Execução Médio (s): %.2f\n', media_tempo);
    end
    if(app.ErroCheckBox.Value) == 1
        fprintf('- Erro MSE Médio: %.2f\n', media_erro);
    end
    fprintf('\n-------------------------------------------------------------------------------------------------------\n');
    % Melhor Rede Global
    fprintf('\nMelhor Rede Global:\n');
    fprintf('- Precisão Total: %.2f%%\n', melhorPrecisaoTotal_G);
    fprintf('- Precisão Teste: %.2f%%\n', melhorPrecisaoTeste_G);
    if(app.TempoExecucao.Value) == 1
        fprintf('- Tempo de Execução (s): %.2f\n', melhorTempo_G);
    end
    if(app.ErroCheckBox.Value) == 1
        fprintf('- Erro MSE: %.2f\n', melhorErro_G);
    end
    fprintf('- Iteração: %d\n', melhorIndice_G);
    fprintf('\n-------------------------------------------------------------------------------------------------------\n');
    % Melhor Rede Teste
    fprintf('\nMelhor Rede Teste:\n');
    fprintf('- Precisão Total: %.2f%%\n', melhorPrecisaoTotal_T);
    fprintf('- Precisão Teste: %.2f%%\n', melhorPrecisaoTeste_T);
    if(app.TempoExecucao.Value) == 1
        fprintf('- Tempo de Execução (s): %.2f\n', melhorTempo_T);
    end
    if(app.ErroCheckBox.Value) == 1
        fprintf('- Erro MSE: %.2f\n', melhorErro_T);
    end
    fprintf('- Iteração: %d\n', melhorIndice_T);
end

function precisao = calcularPrecisao(out, targets)
    r = 0;
    for i = 1:size(out, 2)
        [~, b] = max(out(:, i));
        [~, d] = max(targets(:, i));
        if b == d
            r = r + 1;
        end
    end
    precisao = r / size(out, 2) * 100;
end
