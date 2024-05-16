function testHeart()

S = readmatrix('heart_test.csv', 'Delimiter', ',', 'DecimalSeparator', '.');

load('nn_heart1.mat');
target = [1 1 1 0 0 0];

y = sim(net, S');

y = y >= 0.5;

if isequal(y_bin, target)
    disp('Classificação correta!');
else
    disp('Classificação incorreta!');

end