function [retrieved_indexes, similarities, new_case] = retrieve(case_library, new_case, threshold)
    retrieved_indexes = [];
    similarities = [];
    closest_similarity = 0;
    closest_case = [];

    atributos = {'gender', 'age', 'hypertension', 'heart_disease', 'ever_married', 'Residence_type', 'avg_glucose_level', 'bmi', 'smoking_status'};
    
    for i = 1:size(case_library, 1)
        similarity = calculate_similarity(case_library(i, :), new_case, atributos);
        
        % Se a similaridade for válida e >= ao threshold
        if ~isnan(similarity) && similarity >= threshold && similarity < 1
            similarities = [similarities, similarity];
            retrieved_indexes = [retrieved_indexes; i];

            if similarity > closest_similarity
                closest_similarity = similarity;
                closest_case = case_library(i, :);
            end
        end
        
        % Informação do progresso
        % fprintf('[%d] de [%d] tem similaridade de %.2f%%...\n', i, size(case_library, 1), similarity * 100);
    end

    if isempty(retrieved_indexes)
        fprintf('\nNão existe nenhum caso com similaridade >= %.2f\n', threshold);
    else
        fprintf('\n%d Caso(s) com similaridade >= %.2f\n', length(retrieved_indexes), threshold);
    end

    if ~isempty(closest_case)
        fprintf('\nCaso mais próximo de similaridade 1 encontrado:\n\n');
        %disp(closest_case);
    end
    
    fprintf('Fase retrieve completa..\n');
end


function similarity = calculate_similarity(existing_case, new_case, atributos)
    similarity = 0;
    weighting_factors = [1, 4, 5, 4, 1, 1, 3, 2, 3];
    
    % Para cada atributo relevante
    for i = 1:numel(atributos)
        attribute = atributos{i};
        attribute_weight = weighting_factors(i);
        
        % Calcular similaridade para cada tipo de atributo
        switch attribute
            case {'gender','hypertension', 'heart_disease', 'ever_married', 'Residence_type'}
                attribute_similarity = calculate_binary_distance(existing_case.(attribute), new_case.(attribute));
            case 'age'
                min_value = 0;
                max_value = 90;
                attribute_similarity = calculate_intervalo_similarity(existing_case.(attribute), new_case.(attribute),min_value,max_value);
            case 'avg_glucose_level'
                min_value = 0;
                max_value = 275;
                attribute_similarity = calculate_intervalo_similarity(existing_case.(attribute), new_case.(attribute),min_value,max_value);
            case'bmi'
                min_value = 0;
                max_value = 60;
                attribute_similarity = calculate_intervalo_similarity(existing_case.(attribute), new_case.(attribute),min_value,max_value);
            case 'smoking_status'
                attribute_similarity = calculate_smoking_status_similarity(existing_case.(attribute), new_case.(attribute));
        end

        % Aplicar o peso ao valor da similaridade do atributo
        similarity = similarity + attribute_similarity * attribute_weight;
    end
    
    % Normalizar pela soma dos pesos para manter a similaridade entre 0 e 1
    similarity = similarity / sum(weighting_factors);
end


function similarity = calculate_binary_distance(val1, val2)
    % 1 se iguais, 0 cc
    similarity = double(val1 == val2);

    % fprintf('calculate_binary_distance\n');
    % fprintf('Valores de entrada: val1 = %d, val2 = %d\n', val1, val2);
    % fprintf('Similaridade calculada: %.2f\n', similarity);
end

function similarity = calculate_intervalo_similarity(value1, value2,min_value,max_value) 
    % Distância entre valores
    distance = abs(value1 - value2);
    
    % Calcule a similaridade como 1 menos a proporção da distância sobre o intervalo
    similarity = 1 - (distance / (max_value - min_value));
    
    % Assegure-se de que a similaridade está entre 0 e 1
    similarity = max(0, min(1, similarity));

    % fprintf('calculate_intervalo_similarity do atributo\n');
    % fprintf('Valores de entrada: val1 = %f, val2 = %f\n , max_value = %d , min_value = %d', value1, value2,max_value,min_value);
    % fprintf('Similaridade calculada: %.2f\n', similarity);
end

function similarity = calculate_smoking_status_similarity(val1, val2)
    if val1 == val2 
        similarity = 1; 
    elseif (val1 == 1 && val2 == 2) || (val1 == 2 && val2 == 1)
        similarity = 0.60;
    elseif (val1 == 1 && val2 == 0) || (val1 == 0 && val2 == 1)
        similarity = 0.25;
    else
        similarity = 0; % Diferentes
    end
    
    % fprintf('calculate_smoking_status_distance\n');
    % fprintf('Valores de entrada: val1 = %d, val2 = %d\n', val1, val2);
    % fprintf('Similaridade calculada: %.2f\n', similarity);
end

