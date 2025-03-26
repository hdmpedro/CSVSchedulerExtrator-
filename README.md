# CSVSchedulerExtrator-
Sistema Scheduler modular que agenda uma consulta e extração de certos dados de multiplos bancos de dados e os salva como arquivos .CSV.

O sistema recebe todos os parâmetros de configuração a partir de um arquivo .XML(config.xml), bem como; informações de conexão dos bancos de dados, data e frequência de execução via cron-expression, query SQL que será executada, diretório e nome do arquivo .CSV.
