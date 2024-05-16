function paridade_parPerceptrao_FFN()

% limpar
clear all;
close all;

% inicializar entrada
p = [1 0 0 1 0 0 1 0 1 1; %4 entradas quer dizer 4 linhas
     0 1 0 1 1 0 1 1 0 1;
     0 0 1 0 1 1 1 1 1 1;
     0 0 0 0 0 1 0 1 1 1];

t = [0 0 0 1 1 1 0 0 0 1]; %o target é sempre o resultado em colunas mediante o problema posto neste caso o target na posiçao 0 é 1 pq existem 4 1's na 1a coluna logo como é um numero par de 1's da 1

% fprintf("Rede Neuronal:\n");
% net = perceptron;
% net.divideFcn = '';
% net.layers{1}.transferFcn = 'tansig';
% net.trainParam.epochs = 100;
% net = train(net, p, t);
% out_sim = net(p);
% out_sim = out_sim >= 0.5; % esta linha arredonda o valor dos resultados
% disp(out_sim);
% erro = perform(net, t, out_sim);
% fprintf("Erro perceptron %f\n", erro);

%a alinea d diz para solucionar o problema com uma camada escondida logo
%teria que usar o feedforwardnet
%RESOLUCAO
fprintf("Rede Neuronal:\n");
net = feedforwardnet;
net.divideFcn = '';
net = train(net, p, t);
out_sim = net(p);
out_sim = out_sim >= 0.5; % esta linha arredonda o valor dos resultados
disp(out_sim);
erro = perform(net, t, out_sim);
fprintf("Erro perceptron %f\n", erro);

end