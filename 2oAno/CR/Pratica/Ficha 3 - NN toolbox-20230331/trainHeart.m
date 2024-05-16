function trainHeart()

%Leitura do ficheiro de treino
S = readmatrix('heart_train.csv', 'Delimiter', ',', 'DecimalSeparator', '.');

%Matriz de entrada e targets
inputs = S(:, 1:13)';
targets = S(:, 14)';

%Criação da Rede Neural
net = feedforwardnet;

%Treino da Rede Neural
net = train(net, inputs, targets);

%Simulação da Rede Neural com os mesmos exemplos do treino
out_sim = sim(net, inputs);

%Arredondamento para binário
out_sim = out_sim >= 0.5;

error = perform(net, targets, out_sim);
best_net = net;
best_error = error;

fprintf("Rede Neural: \n\tClassificação doentes cardiacos:");
disp(out_sim);
fprintf("Erro feedforwardnet: %f\n", error);

%Teste de tiologias diferentes
for num_layers = 1 : 5
    for num_neuronios = 5 : 5 : 100
        
        net = feedforwardnet(repmat(num_neuronios, 1, num_layers));

        %Treino da Rede Neural
        net = train(net, inputs, targets);
        
        %Simulação da Rede Neural com os mesmos exemplos do treino
        out_sim = sim(net, inputs);
        
        %Arredondamento para binário
        out_sim = out_sim >= 0.5;
        
        error = perform(net, targets, out_sim);

        %Mostrar resultados
        %fprintf("Resultados para a seguinte tipologia: \n");
        %fprintf("Numero de camadas: %d\n", num_layers);
        %fprintf("Numero de Neuronios: %d\n", num_neuronios);

        %Guradar a rede que tem melhor resultado
        if error > best_error
            best_error = error;
            best_net = net;
            save('nn_heart1.mat','net');
    end
end

end