function train()
    clear all;
    close all;

    iteracoes = 50; %aqui seria o valor do app.sliderIteracoes
    pathConfig = 'testes/redes/default.mat'; %aqui seria o valor do app.nomeMelhorRede + '.mat'

    % Médias
    media_tempo = 0;
    media_precisao_total = 0;
    media_precisao_teste = 0;
    media_erro = 0;
    
    % Melhor
    melhorIndice = 0;
    melhorPrecisaoTotal = 0;
    melhorPrecisaoTeste = 0;
    melhorErro = 0;
    melhorTempo = 0;
    melhorRede = struct();

    % Carrega o dataset
    train_data = readtable('Train.csv'); %aqui seria o nome do ficheiro escolhido no app.escolheDataset
    %train_data(:,1) = []; % Remove a primeira coluna (id)

    %temos q ter atenção a isto, para já testa-se com o train.csv do stroke
    train_inputs = table2array(train_data(:, 2:end-1))'; 
    train_targets = full(ind2vec(train_data.stroke' + 1));
    
    for k = 1:iteracoes
        % Criar a rede neural
        net = feedforwardnet(10); %aqui seria um if de acordo com o app.NdeCamadasDropDown, 
        % se fosse 1 camada seria feedforwardnet(app.nNeuronios) , se
        % fossem 2 camadas seria feedforwardnet([app.nNeuronios,app.nNeuronios]) etc

        % Configuração da rede neural
        net.layers{1}.transferFcn = 'tansig'; %aqui seria o valor do app.fAtivacao1 
        net.layers{2}.transferFcn = 'purelin'; %aqui seria o valor do app.fAtivacao2
        %net.layers{3}.transferFcn = 'purelin'; %aqui seria o valor do app.fAtivacao3
        net.trainFcn = 'trainlm'; %aqui seria o valor do app.fTreino
        net.trainParam.showWindow=0;

        net.divideFcn = 'dividerand';
        net.divideParam.trainRatio = 0.70; %aqui seria o valor do app.segTrain
        net.divideParam.valRatio = 0.15; %aqui seria o valor do app.segValidation
        net.divideParam.testRatio = 0.15; %aqui seria o valor do app.segTest

        % Treinar a rede
        tic; % Iniciar o cronômetro
        [net,tr] = train(net, train_inputs, train_targets);
        tempo_execucao = toc; % Parar o cronômetro

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
        if precisao_total > melhorPrecisaoTotal
            melhorIndice = k;
            melhorPrecisaoTotal = precisao_total;
            melhorPrecisaoTeste = precisao_teste;
            melhorErro = erro_mse;
            melhorTempo = tempo_execucao;
            
            % Guardar a melhor rede
            melhorRede.net = net;
            melhorRede.tr = tr;
        end

        % Mostrar resultados
        fprintf('------------------------------------\n')
        fprintf('Rede [%d]\n',k);
        fprintf('- Precisão Total: %.2f%%\n', precisao_total);
        fprintf('- Precisão Teste: %.2f%%\n', precisao_teste);
        fprintf('- Erro MSE: %.2f\n', erro_mse);
        fprintf('- Tempo de Execução: %.2f segundos\n', tempo_execucao);

        media_precisao_total = media_precisao_total + precisao_total;
        media_precisao_teste = media_precisao_teste + precisao_teste;
        media_tempo = media_tempo + tempo_execucao;
        media_erro = media_erro + erro_mse;
    end

    % Calcular médias
    media_precisao_total = media_precisao_total / iteracoes;
    media_precisao_teste = media_precisao_teste / iteracoes;
    media_tempo = media_tempo / iteracoes;
    media_erro = media_erro / iteracoes;

    save(pathConfig,'melhorRede');

    % Médias
    fprintf('------------------------------------\n')
    fprintf('Média das %d Iterações:\n',iteracoes);
    fprintf('- Precisão Total Média: %.2f%%\n', media_precisao_total);
    fprintf('- Precisão Teste Média: %.2f%%\n', media_precisao_teste);
    fprintf('- Tempo de Execução Médio: %.2f segundos\n', media_tempo);
    fprintf('- Erro MSE Médio: %.2f\n', media_erro);
    
    % Melhor Rede
    fprintf('\nMelhor Rede [%d]\n',melhorIndice);
    fprintf('- Precisão Total: %.2f%%\n', melhorPrecisaoTotal);
    fprintf('- Precisão Teste: %.2f%%\n', melhorPrecisaoTeste);
    fprintf('- Tempo de Execução: %.2f segundos\n', melhorTempo);
    fprintf('- Erro MSE: %.2f\n', melhorErro);
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
