function [] = cbr()

    similarity_threshold = 0.90;
    ficheiro = 'Train1.csv';
    
    % Verifica se o arquivo CSV contém NaN na coluna "stroke"
    file_contents = fileread(ficheiro);
    if contains(file_contents, 'NaN')
        % Defina o formato de leitura do arquivo CSV
        formatSpec = '%f%f%f%f%f%f%f%f%f%f%f';
    
        % Leia o arquivo CSV, tratando os valores 'NA' como valores ausentes
        case_library = readtable(ficheiro, ...
        'Delimiter', ',', ...
        'Format', formatSpec);

        strokeNA = find(isnan(case_library.stroke));

        % Percorre todas as linhas com stroke NaN
        for i = strokeNA'    
            % Configura new_case com os valores da linha encontrada
            new_case.gender = case_library.gender(i);
            new_case.age = case_library.age(i);
            new_case.hypertension = case_library.hypertension(i);
            new_case.heart_disease = case_library.heart_disease(i);
            new_case.ever_married = case_library.ever_married(i);
            new_case.Residence_type = case_library.Residence_type(i);
            new_case.avg_glucose_level = case_library.avg_glucose_level(i);
            new_case.bmi = case_library.bmi(i);
            new_case.smoking_status = case_library.smoking_status(i);
    
            fprintf('\nIniciando fase do retrieve para o índice [%d] ...', i+1);
    
            % Chama a função retrieve para o new_case configurado
            [retrieved_indexes, similarities, updated_case] = retrieve(case_library, new_case, similarity_threshold);
            
            %disp(updated_case);

            % Ignora o caso próprio (similaridade de 1) e encontra o maior abaixo de 1
            valid_similarities = similarities < 1;
            filtered_similarities = similarities(valid_similarities);
            filtered_indexes = retrieved_indexes(valid_similarities);
    
            % Encontra o índice com a maior similaridade válida
            if ~isempty(filtered_similarities)
                [max_similarity, max_index] = max(filtered_similarities);
    
                % Verifica se a maior similaridade é maior que o threshold
                if max_similarity > similarity_threshold
                    % Pega o valor de stroke do caso mais semelhante
                    similar_case_stroke = case_library.stroke(filtered_indexes(max_index));
                    % Atualiza o valor de stroke na linha original
                    case_library.stroke(i) = similar_case_stroke;
                    fprintf('\nAtualizado o valor de stroke para o índice %d com o valor do caso mais similar.\n', i+1);
                else
                    fprintf('Nenhum caso recuperado com similaridade acima de %f para o índice [%d] .\n', similarity_threshold, i+1);
                end
            else
                fprintf('Não foram encontrados casos válidos para o índice [%d] .\n', i + 1);
            end
           fprintf('\n-------------------------------------------------------------------------------------------------------\n');  
        end

        if numel(strokeNA) ~= 0
            writetable(case_library, ficheiro);
            fprintf('\nArquivo CSV atualizado e guardado.\n\n');
        end
    
    else
        disp('Não existem valores desconhecidos neste dataset.');
    end    
end
