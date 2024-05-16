%a)

%a = [2:2:100];
%disp(a)

%b)

%aTransposta = a';
%disp(aTransposta)

%c)

%a = [1 2 3; 4 5 6; 7 8 9];
%disp(a);

%d)

%a(1,3)=33; %ou a(7)=33
%disp(a);

%e)

%a = ones(3)

%f) 

%a = rand(8); %reais
%b = randn(8); %negativos
%c = randi(8,8); %inteiros
%d = randi(100,3,5); %1-100 3x5

%disp(d)

%g)

%a(:, 2) = []; % apagar a segunda coluna
%disp(a)

%h)

%x = 0:pi/100:2*pi;
%y = sin(x);

%plot(x, y);

%i)

x = 0:pi/100:2*pi;
y1 = sin(x);
y2 = cos(x);

%plot(x, y1);
%hold on;
%plot(x, y2);

%j)
    %a) 
    legend('sin','cos');

    %b)
    title('Alinea j.b)');

    %c)
    help plot

    plot(x, y1,'g');
    hold on;
    plot(x, y2);


