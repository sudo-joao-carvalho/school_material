function rn3b()
%Funcao rn3b: cria, treina e testa uma RN feedforward
%usando as funcoes da NNTool

% limpar
clear all;
close all;

% inicializar entrada
p = [0 0 1 1; 0 1 0 1];

%informacao sobre operador logico 
fprintf('Introduza operador logico desejado:\n');
fprintf('1 - AND\n');
fprintf('2 - OR\n');
fprintf('3 - NAND\n');
fprintf('4 - XOR\n');
tmp =  input('                        operador? (default 1) = ');

% inicializar targets
if isempty(tmp)
    t = [0 0 0 1];
    op='AND';
else
    switch tmp
        case 1
            t = [0 0 0 1];
            op='AND';
        case 2
            t = [0 1 1 1]; % COMPLETAR:
            op='OR';
        case 3
            t = [1 1 1 0]; % COMPLETAR:
            op='NAND';
        case 4
            t = [0 1 1 0]; % COMPLETAR:
            op='XOR';
        otherwise
            t = [0 0 0 1];
            op='AND';
    end
end


% COMPLETAR: criar RN chamada net
net = feedforwardnet(10); % alternativas: net = feedforwardnet(5) --> cria uma rede neuronal feedforward com 5 neuronios
                          %               net = feedforwardnet([10 5]) --> cria uma rede neuronal feedforward com duas camadas internas uma com 10 neuronios e outra com 5    

% COMPLETAR: ajustar os parametros seguintes

    %Alterar funcao de treino
        %net.trainFcn = 'traingdx';
        %net.trainParam.epochs = 100;

    %Alterar funcoes de ativaçao (.layers tem que ver com o numero de camadas que a rede neuronal tem)
        %net.layers{1}.transferFcn = 'logsig';
        %net.layers{2}.transferFcn = 'logsig';

    %Segmentacao de exemplos
        %usar todos os exemplos: net.divideFcn = '';

% FUNCAO DE ATIVACAO DA CAMADA DE SAIDA
net.layers{1}.transferFcn = 'tansig';
% FUNCAO DE TREINO 
net.trainFcn = 'traingdx';
%4. c) net.trainFcn = 'trainlim';
% NUMERO DE EPOCAS DE TREINO
net.trainParam.epochs = 100;

% TODOS OS EXEMPLOS DE INPUT SAO USADOS NO TREINO
net.divideFcn = '';                 

% COMPLETAR: treinar a rede
net = train(net, p, t);

% visualizar a rede
view(net)

% COMPLETAR simular a rede e guardar o resultado na variavel y
y = sim(net, p);

% Mostrar resultado
y = (y >= 0.5);
fprintf('Saida da RN para %s:', op);
disp(y);
fprintf('Saida desejada para %s:', op);
disp(t);

end
