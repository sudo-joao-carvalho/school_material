function [new_price] = reuse(retrieved_cases, new_case)

    x1 = retrieved_cases{:,4}; % Number of Persons
    x2 = retrieved_cases{:,7}; % Duration
    y = retrieved_cases{:,3}; % Prices
    
    % Multiple Regression
    % Adapted from https://www.mathworks.com/examples/matlab/mw/matlab-ex88655142-multiple-regression
    
    X = [ones(size(x1)) x1 x2];
    
    b = X\y;
    
    % new_price = ...
        
    fprintf(['Based on the attributes Number of Persons and Duration of the retrieved cases,\n', ...
                'the estimated price for the new case is %.2f, instead of %.2f.'], new_price, new_case.price);

end

