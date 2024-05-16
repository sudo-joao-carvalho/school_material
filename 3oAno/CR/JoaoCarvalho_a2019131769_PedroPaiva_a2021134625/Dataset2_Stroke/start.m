function start() %START
% Funcao rn7b_start: cria, treina e testa uma RN feedforward com dados do CSV

% Limpar
clear all;
close all;
iteracoes = 50;
mediaTempo = 0;
mediaPrecisao = 0;
mediaErro = 0;

% Carregar dados
data = readtable('Start.csv');

% Preparar variáveis de entrada (todas as colunas exceto 'id' e 'stroke')
inputs = data{:, 2:end-1}; % Assumindo que 'id' é a primeira coluna e 'stroke' é a última
inputs = double(inputs); % Converter para double para processamento

% Preparar variável target (coluna 'stroke')
target = data.stroke;
target = double(target)'; % Converter para double e transpor para combinar com a entrada

for i = 1:iteracoes
    % Criar RN com 10 nós na camada escondida
    net = feedforwardnet(); % 10 por default.
    
    net.layers{1}.transferFcn = 'tansig';
    net.layers{2}.transferFcn = 'purelin';
    
    net.trainFcn = 'trainlm';
    net.trainParam.showWindow = 0;
    
    net.divideFcn = '';
    
    % Treinar a rede
    [net, tr] = train(net, inputs', target);
    
    % Visualizar a rede
    %view(net)
    
    % Simular a rede e guardar o resultado na variável y
    y = net(inputs');
    
    % Calcular métricas de desempenho
    accuracy = sum(round(y) == target) / numel(target);
    mse_error = perform(net, target, y);
    mediaErro = mediaErro + mse_error;
    
    % Mostrar resultado
    fprintf('Precisão Total: %.2f%%\n', accuracy * 100);
    fprintf('Erro MSE: %f\n', mse_error);
    mediaPrecisao = mediaPrecisao + (accuracy * 100);
    
    % Registrar o tempo de execução
    executionTime = tr.time(end); % Tempo total de treinamento
    mediaTempo = mediaTempo + executionTime;

    fprintf('Tempo de Execução: %.2f segundos\n', executionTime);
end
    fprintf('-----------------------------------------------------\n');
    fprintf('Médias:\n');
    fprintf('Precisão total média: %.2f %%\n', mediaPrecisao/iteracoes);
    fprintf('Tempo de execução: %.2f segundos\n', mediaTempo/iteracoes);
    fprintf('Erro: %.2f\n', mediaErro/iteracoes);
end