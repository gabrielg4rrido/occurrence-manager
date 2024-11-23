ALTER TYPE estado_ocorrencia ADD VALUE 'ATIVA';

UPDATE Ocorrencia
SET sta_ocorrencia = 'ATIVA'
WHERE sta_ocorrencia = 'PENDENTE';