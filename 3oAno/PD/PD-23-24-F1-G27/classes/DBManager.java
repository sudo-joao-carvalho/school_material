package resources.db;
import javax.script.SimpleScriptContext;
import javax.swing.plaf.nimbus.State;
import java.io.*;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class DBManager {
    private Connection conn;

    private String executedQuery;

    public DBManager() throws SQLException {
        this.conn = DriverManager.getConnection("jdbc:sqlite:resources/db/PD-2023-24-TP.db");

        insertVersionDB();
    }

    public boolean connectToDB(String directory) {
        if (new File(directory + "/PD-2023-24-TP.db").exists()) {
            try {
                this.conn = DriverManager.getConnection("jdbc:sqlite:" + directory + "/PD-2023-24-TP.db");
            } catch(SQLException e) {
                e.printStackTrace();
                return false;
            }

            return true;
        }

        File file = new File("resources/db/PD-2023-24-TP.db");

        FileInputStream fis;

        try {
            fis = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }

        FileOutputStream fos;

        try{
            fos = new FileOutputStream(directory + "/PD-2023-24-TP.db");
        }catch (FileNotFoundException e){
            return false;
        }

        byte[] buf = new byte[1024];

        int nBytes = 0;

        while (nBytes >= 0) {
            try {
                nBytes = fis.read(buf);
                fos.write(buf);
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }

        try {
            fos.close();
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            this.conn = DriverManager.getConnection("jdbc:sqlite:" + directory + "/PD-2023-24-TP.db"/*"/PD-2023-24-TP-" + port + ".db"*/);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public void close() throws SQLException
    {
        if (conn != null)
            conn.close();
    }

    public String listAllUsers(Integer id) throws SQLException {
        Statement statement = conn.createStatement();

        String sqlQuery = "SELECT Id, Nome, Admin, Autenticado, Email, Password, NIF FROM utilizador";

        if (id != -1)
            sqlQuery += " WHERE Id like '%" + id + "%'";

        ResultSet resultSet = statement.executeQuery(sqlQuery);

        StringBuilder str = new StringBuilder();
        str.append("ID\tUsername\tNome\tAdministrador\tAutenticado\tPassword\tEmail\t\t\t\t\tNIF\t");

        while(resultSet.next()){
            int Id = resultSet.getInt("Id");
            String username = resultSet.getString("Nome");
            String nome = resultSet.getString("nome");
            int administrador = resultSet.getInt("Admin");
            int autenticado = resultSet.getInt("Autenticado");
            String email = resultSet.getString("Email");
            String password = resultSet.getString("Password");
            int nif = resultSet.getInt("NIF");


            str.append(id).append("\t").append(username).append("\t").append(nome);
            str.append("\t\t").append(administrador).append("\t\t").append(autenticado).append("\t\t").append(password).append("\t\t").append(email).append("\t\t").append(nif);
        }

        resultSet.close();
        statement.close();

        return str.toString();
    }

    public boolean insertEvent(ArrayList<String> params) {
        Statement statement;

        try {
            statement = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        int i = 0;

        String sqlQuery = "INSERT INTO Evento VALUES (NULL, 0, '" +
                params.get(i++) + "' , '" + params.get(i++) + "' , '" +
                params.get(i++) + "' , '" + params.get(i++) + "' , '" +
                params.get(i) + "', 0)";

        try {
            statement.executeUpdate(sqlQuery);
            updateDBVersion();
            this.executedQuery = sqlQuery;
            return true; // já não devolve o id do novo evento
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public int insertUser(ArrayList<String> userParameters){

        Statement statement = null;
        try{
            statement = conn.createStatement();
        }catch (SQLException e){
            return 0;
        }


        boolean existeRegisto = false;
        int idRegisto = 0;

        // Verificar se há algum com nome ou utilizador igual
        String verificar = "SELECT 1 FROM utilizador WHERE lower(email) = lower('" + userParameters.get(1) + "') OR lower(password) = lower('" + userParameters.get(3) + "')";
        try {
            ResultSet resultSet = statement.executeQuery(verificar);

            // Se houver algum registo no ResultSe"t, definimos existeRegistro como true
            existeRegisto = resultSet.next();

        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (existeRegisto) {
            return 0;
        }

        int i = 0;

        String sqlQuery = "INSERT INTO utilizador VALUES (NULL, '" + userParameters.get(i++) + "' , '" +
                userParameters.get(i++) + "' , '" + userParameters.get(i++) + "' , '" +
                userParameters.get(i++) + "' , '" + userParameters.get(i++) + "' , '" + userParameters.get(i++) + "')";

        try{
            statement.executeUpdate(sqlQuery);
        }catch (SQLException e){
            e.printStackTrace();
            return 0;
        }finally {
            try{
                statement.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }

        String getId = "SELECT id FROM utilizador WHERE lower(email) = lower('" + userParameters.get(1) + "') OR lower(password) = lower('" + userParameters.get(3) + "')";
        try {
            ResultSet resultSet = statement.executeQuery(getId);

            idRegisto = resultSet.getInt("id");
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        updateDBVersion();
        this.executedQuery = sqlQuery;
        return idRegisto;
    }

    public int[] verifyLogin(ArrayList<String> params){

        Statement statement = null;
        try{
            statement = conn.createStatement();
        }catch (SQLException e){
            return null;
        }

        int idRegisto = 0;
        int isAdmin = 0;

        String verificar = "SELECT id, admin FROM utilizador WHERE lower(email) = lower('" + params.get(0) + "') AND lower(password) = lower('" + params.get(1) + "')";
        try {
            ResultSet resultSet = statement.executeQuery(verificar);

            idRegisto = resultSet.getInt("id");
            isAdmin = resultSet.getInt("admin");
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return new int[]{idRegisto, isAdmin};
    }

    public String listPresencas(Integer idEvento, Integer idClient) {

        Statement statement = null;
        try{
            statement = conn.createStatement();
        }catch (SQLException e){
            return "";
        }

        String sqlQuery = null;

        if(idEvento == -1){
            sqlQuery = "SELECT evento.Nome, evento.Local, evento.Data, evento.HoraInicio " +
                    "FROM Evento evento " +
                    "JOIN Presenca presenca ON evento.Id = presenca.IdEvento " +
                    "JOIN Utilizador utilizador ON presenca.IdUtilizador = utilizador.Id " +
                    "WHERE utilizador.Id=" + idClient;

        }else{
            sqlQuery = "SELECT distinct evento.Nome, evento.Local, evento.Data, evento.HoraInicio FROM evento " +
                    "JOIN presenca ON evento.Id = presenca.IdEvento " +
                    "JOIN utilizador ON utilizador.Id = presenca.IdUtilizador " +
                    "WHERE evento.Id=" + idEvento +
                    " AND utilizador.Id=" + idClient;
        }


        ResultSet resultSet = null;
        StringBuilder str = new StringBuilder();
        try{
            resultSet = statement.executeQuery(sqlQuery);

            str.append("ID\tID Evento\tID Utilizador\t");

            while(resultSet.next()){
                String nome = resultSet.getString("Nome");
                String local = resultSet.getString("Local");
                String data = resultSet.getString("Data");
                String horaInicio = resultSet.getString("HoraInicio");

                str.append("\n").append(idEvento).append("\t").append(nome).append("\t").append(local);
                str.append("\t\t").append(data).append(horaInicio).append("\t\t");
            }

        }catch (SQLException e){
            e.printStackTrace();
            return "";
        }finally {
            try{
                if(resultSet != null) resultSet.close();
                statement.close();
            }catch (SQLException e){

            }
        }

        return str.toString();
    }

    public boolean editProfile(ArrayList<String> params, Integer id){

        Statement statement = null;
        try{
            statement = conn.createStatement();
        }catch (SQLException e){
            return false;
        }

        if(params.get(0).equalsIgnoreCase("name")){
            String newName = params.get(1);
            String sqlQuery = "UPDATE utilizador SET Nome = '" + newName + "' WHERE id = '" + id + "'";

            try{
                statement.executeUpdate(sqlQuery);
            }catch (SQLException e){
                e.printStackTrace();
                return false;
            }finally {
                try{
                    statement.close();
                }catch (SQLException e){

                }
            }

            updateDBVersion();
            this.executedQuery = sqlQuery;
            return true;
        }else if(params.get(0).equalsIgnoreCase("email")){
            String newEmail = params.get(1);
            String sqlQuery = "UPDATE utilizador SET Email = '" + newEmail + "' WHERE id = '" + id + "'";

            try{
                statement.executeUpdate(sqlQuery);
            }catch (SQLException e){
                e.printStackTrace();
                return false;
            }finally {
                try{
                    statement.close();
                }catch (SQLException e){

                }
            }

            updateDBVersion();
            this.executedQuery = sqlQuery;
            return true;
        }else if(params.get(0).equalsIgnoreCase("password")){
            String newPassword = params.get(1);
            String sqlQuery = "UPDATE utilizador SET password = '" + newPassword + "' WHERE id = '" + id + "'";

            try{
                statement.executeUpdate(sqlQuery);
            }catch (SQLException e){
                e.printStackTrace();
                return false;
            }finally {
                try{
                    statement.close();
                }catch (SQLException e){

                }
            }

            updateDBVersion();
            this.executedQuery = sqlQuery;
            return true;
        }if(params.get(0).equalsIgnoreCase("nif")){
            int newNif = Integer.parseInt(params.get(1));
            String sqlQuery = "UPDATE utilizador SET nif = '" + newNif + "' WHERE id = '" + id + "'";

            try{
                statement.executeUpdate(sqlQuery);
            }catch (SQLException e){
                e.printStackTrace();
                return false;
            }finally {
                try{
                    statement.close();
                }catch (SQLException e){

                }
            }

            updateDBVersion();
            this.executedQuery = sqlQuery;
            return true;
        }

        return false;
    }

    public boolean checkForUserAttendance(int userId){
        Statement statement = null;

        try {
            statement = conn.createStatement();

            //retirar o id do evento pq falha se o user ja estiver registado quer naquele evento ou em qualquer outro

            String sqlQuery = "SELECT Id FROM Presenca WHERE IdUtilizador='" + userId + "'";

            int count = statement.executeQuery(sqlQuery).getInt("Id");

            if (count != 0) { // significa que o user tem presença em X evento
                return true;
            }

            return false;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return false;
    }

    public boolean checkIfEventNow(String horaFim){
        String[] elements = horaFim.split(":");

        int hora = Integer.parseInt(elements[0]);
        int minuto = Integer.parseInt(elements[1]);

        int horaNow = LocalTime.now().getHour();
        int minutoNow = LocalTime.now().getMinute();

        if(hora >= horaNow && minuto >= minutoNow){
            return true;
        }

        return false;
    }


    public boolean checkEventCodeAndInsertUser(int eventCode, int userId) {

        Statement statement = null;

        try {
            statement = conn.createStatement();

            String sqlQuery = "SELECT Id, HoraFim FROM Evento WHERE Codigo='" + eventCode + "'";

            int value = statement.executeQuery(sqlQuery).getInt("Id");
            String horaFim = statement.executeQuery(sqlQuery).getString("HoraFim");

            sqlQuery = "SELECT CodeExpireTime AS expireTime FROM Evento WHERE Codigo='" + eventCode + "'";
            String expireTime = statement.executeQuery(sqlQuery).getString("expireTime");

            LocalTime dbExpireTime = LocalTime.parse(expireTime, DateTimeFormatter.ofPattern("HH:mm"));

            if (value == 0) {
                return false;
            }

            if(checkForUserAttendance(userId)){
                System.out.println("ja esta num evento nessa hora");
                return false;
            }

            if(LocalTime.now().isAfter(dbExpireTime)){
                System.out.println("codigo expirado");
                return false;
            }

            if(!checkIfEventNow(horaFim)){
                System.out.println("evento nao esta a decorrer");
                return false;
            }

            String insertUserQuery = "INSERT INTO Presenca VALUES (NULL, '" + value + "', '" + userId + "')";

            statement.executeUpdate(insertUserQuery);

            updateDBVersion();
            this.executedQuery = sqlQuery;
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    public boolean insertUserInEvent(ArrayList<String> params) {
        Statement statement = null;

        try {
            statement = conn.createStatement();

            String checkQuery = "SELECT Count(*) FROM Evento WHERE lower(Nome)='" + params.get(1).toLowerCase() + "'";

            int count = statement.executeQuery(checkQuery).getInt(1);

            if (count == 0) {
                return false;
            }

            String checkUserQuery = "SELECT Count(*) FROM Utilizador WHERE lower(Email)='" + params.get(0).toLowerCase() + "'";

            int countUsers = statement.executeQuery(checkUserQuery).getInt(1);

            if (countUsers == 0) {
                return false;
            }

            String sqlQuery = "INSERT INTO presenca VALUES (NULL, (SELECT id FROM Evento WHERE lower(nome)='" + params.get(1).toLowerCase() + "'), (SELECT id FROM utilizador WHERE lower(email)='" + params.get(0).toLowerCase() + "'))";


            statement.executeUpdate(sqlQuery);
            this.executedQuery = sqlQuery;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        updateDBVersion();
        return true;
    }

    public boolean deleteUserFromEvent(ArrayList<String> params) {
        Statement statement = null;

        try {
            statement = conn.createStatement();

            String sqlQuery = "DELETE FROM presenca " +
                    "WHERE idEvento IN (SELECT id FROM Evento WHERE nome = '" + params.get(1) + "') " +
                    "AND idUtilizador IN (SELECT id FROM utilizador WHERE email = '" + params.get(0) + "')";

            statement.executeUpdate(sqlQuery);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        updateDBVersion();
        return true;
    }

    public boolean deleteEvent(int eventId) throws SQLException {

        if (getTotalAttendanceForEventAsInt(eventId) >= 1) { //so pode ser eliminado se n tiver nenhuma presenca
            return false;
        }

        Statement statement = null;

        try {
            statement = conn.createStatement();

            String sqlQuery = "DELETE FROM evento WHERE id=" + eventId;


            int rowsAffected = statement.executeUpdate(sqlQuery);

            if (rowsAffected == 0) {
                return false;
            }

            this.executedQuery = sqlQuery;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }

        }

        updateDBVersion();
        return true;
    }

    public int getTotalAttendanceForEventAsInt(int eventId) throws SQLException {

        if (eventId <= 0) {
            throw new IndexOutOfBoundsException("Invalid Id!");
        }

        Statement statement = null;

        try {
            statement = conn.createStatement();

            String sqlQuery = "SELECT Count(*) AS totalPresencas FROM presenca WHERE Presenca.IdEvento=" + eventId;

            ResultSet rs = statement.executeQuery(sqlQuery);

            return rs.getInt("totalPresencas");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                statement.close();
            }
        }
        return 0;
    }

    public boolean editEventData(Integer eventId, ArrayList<String> params) throws SQLException {

        if (getTotalAttendanceForEventAsInt(eventId) != 0) { // se o evento já tiver uma presença ou mais, então gg
            return false;
        }

        Statement statement = null;

        String sqlQuery = "";

        try {
            statement = conn.createStatement();

            switch (params.get(0)) {
                case "nome" -> {
                    sqlQuery = "UPDATE evento SET nome='" + params.get(1) + "' WHERE id=" + eventId;
                }
                case "local" -> {
                    sqlQuery = "UPDATE evento SET local='" + params.get(1) + "' WHERE id=" + eventId;
                }
                case "data" -> {
                    sqlQuery = "UPDATE evento SET data='" + params.get(1) + "' WHERE id=" + eventId;
                }
                case "horainicio" -> {
                    sqlQuery = "UPDATE evento SET horainicio='" + params.get(1) + "' WHERE id=" + eventId;
                }

                case "horafim" -> {
                    sqlQuery = "UPDATE evento SET horafim='" + params.get(1) + "' WHERE id=" + eventId;
                }
            }
            statement.executeUpdate(sqlQuery);

            this.executedQuery = sqlQuery;
        } catch (SQLException e){
            return false;
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        updateDBVersion();
        return true;
    }

    public String listPresencasFromUserEmail(String email) {
        Statement statement = null;

        try {
            StringBuilder str = new StringBuilder();
            
            statement = conn.createStatement();

            String idUser = "SELECT id FROM utilizador WHERE lower(email) = lower('" + email + "')";

            int id = statement.executeQuery(idUser).getInt("id");

            String sqlQuery = "SELECT evento.Nome, evento.Local, evento.Data, evento.HoraInicio " +
                    "FROM Evento evento " +
                    "JOIN Presenca presenca ON evento.Id = presenca.IdEvento " +
                    "JOIN Utilizador utilizador ON presenca.IdUtilizador = " + id +
                    " WHERE utilizador.Email='" + email + "'";

            ResultSet resultSet = statement.executeQuery(sqlQuery);

            str.append("Nome\t\tLocal\t\tData\t\tHora Inicio\t");

            while(resultSet.next()){
                String nome = resultSet.getString("Nome");
                String local = resultSet.getString("Local");
                String data = resultSet.getString("Data");
                String horaInicio = resultSet.getString("HoraInicio");

                str.append("\n").append(nome).append("\t").append(local).append("\t\t").append(data).append("\t").append(horaInicio);
                str.append("\t\t").append("\t\t");
            }

            return str.toString();

        } catch (SQLException e) {
            e.printStackTrace();
            return "";
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public int addCodeToEvent(Integer eventId, Integer codeExpirationTime){ //TODO so se pode mudar codigo quando o evento ja esta a decorrer

        Statement statement = null;
        try{
            statement = conn.createStatement();
        }catch (SQLException e){
            return 0;
        }

        Random random = new Random();
        int eventCode = random.nextInt(900000) + 100000;

        String sqlTimeCheckerInicio = "SELECT HoraInicio FROM Evento WHERE Id=" + eventId;
        String sqlTimeCheckerFim = "SELECT HoraFim FROM Evento WHERE Id=" + eventId;
        String sqlTimeCheckerData = "SELECT Data FROM Evento WHERE Id=" + eventId;

        try {
            String data = statement.executeQuery(sqlTimeCheckerData).getString("Data");

            LocalDate todayDate = LocalDate.now();

            LocalDate dbDate = LocalDate.parse(data, DateTimeFormatter.ofPattern("dd/MM/yyyy"));

            String horasInicio = statement.executeQuery(sqlTimeCheckerInicio).getString("HoraInicio");

            String horasFim = statement.executeQuery(sqlTimeCheckerFim).getString("HoraFim");

            LocalTime dbTimeInicio = LocalTime.parse(horasInicio, DateTimeFormatter.ofPattern("HH:mm"));

            LocalTime dbTimeFim = LocalTime.parse(horasFim, DateTimeFormatter.ofPattern("HH:mm"));

            if (!(dbTimeInicio.isBefore(LocalTime.now()) && dbTimeFim.isAfter(LocalTime.now())) && !dbDate.equals(todayDate)) {
                return -2;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        LocalTime timeNow = LocalTime.now();

        LocalDateTime localDateTimeAtual = LocalDateTime.of(1, 1, 1, timeNow.getHour(), timeNow.getMinute(), timeNow.getSecond());
        LocalDateTime localDateTimeComMinutosAdicionados = localDateTimeAtual.plusMinutes(codeExpirationTime);
        LocalTime localTimeComMinutosAdicionados = localDateTimeComMinutosAdicionados.toLocalTime();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        String localTimeFormatado = localTimeComMinutosAdicionados.format(formatter);

        String sqlQuery = "UPDATE evento SET codigo = '" + eventCode + "', codeExpireTime = '" + localTimeFormatado + "' WHERE id = " + eventId;

        try {


            int rowsAffected = statement.executeUpdate(sqlQuery);

            if (rowsAffected == 0) {

                return 0;
            }else{
                this.executedQuery = sqlQuery;
                updateDBVersion();
                return eventCode;
            }


        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public int getDBVersion() {

        int versionNumber = 0;

        Statement statement = null;

        try {
            statement = conn.createStatement();

            String sqlQuery = "SELECT Versao FROM Versao";

            versionNumber = statement.executeQuery(sqlQuery).getInt("Versao");

            return versionNumber;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean insertVersionDB() {
        Statement statement = null;

        try {
            statement = conn.createStatement();

            String sqlQuery = "SELECT Count(*) FROM Versao";

            int getCount = statement.executeQuery(sqlQuery).getInt(1);

            if (getCount == 0) {
                String insertQuery = "INSERT INTO Versao VALUES (NULL, 0)";
                statement.executeUpdate(insertQuery);
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return false;
    }

    public boolean updateDBVersion() {
        Statement statement = null;

        int versionNumber = getDBVersion();

        try {
            statement = conn.createStatement();

            String sqlQuery = "UPDATE Versao SET Versao='" + ++versionNumber + "'WHERE id=" + 1;

            statement.executeUpdate(sqlQuery);

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return true;
    }


    public boolean getCSV(int userId) {
        Statement statement = null;

        try {
            statement = conn.createStatement();

            statement.execute("PRAGMA header = on");
            statement.execute("PRAGMA mode = csv");

            String userQuery = "SELECT Nome, NIF, Email FROM Utilizador WHERE Id='" + userId + "'";
            ResultSet userResult = statement.executeQuery(userQuery);

            String eventQuery = "SELECT Evento.Nome as nomeEvento, Evento.Local, Evento.Data, Evento.HoraInicio FROM Evento evento " +
                    "JOIN Presenca presenca ON evento.Id = presenca.IdEvento " +
                    "JOIN Utilizador utilizador ON utilizador.Id = presenca.IdUtilizador " +
                    "WHERE utilizador.Id='" + userId + "'";

            File file = new File("resources/files/presencas.csv");
            try (FileWriter csvWriter = new FileWriter(file)) {
                csvWriter.append("NomeCliente,NIF,Email\n");

                csvWriter.append(userResult.getString("Nome"))
                        .append(",")
                        .append(userResult.getString("NIF"))
                        .append(",")
                        .append(userResult.getString("Email"))
                        .append("\n\n");


                csvWriter.append("NomeEvento, Local, Data, Hora Inicio\n");

                ResultSet eventResult = statement.executeQuery(eventQuery);

               while (eventResult.next()) {
                    csvWriter
                            .append(eventResult.getString("NomeEvento"))
                            .append(",")
                            .append(eventResult.getString("Local"))
                            .append(",")
                            .append(eventResult.getString("Data"))
                            .append(",")
                            .append(eventResult.getString("HoraInicio"))
                            .append("\n");
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return false;
    }

    public boolean getCSVAdmin(int eventId) {
        Statement statement = null;
        try {
            statement = conn.createStatement();

            String sqlQuery = "SELECT Nome, Local, Data, HoraInicio, HoraFim FROM Evento WHERE Id='" + eventId + "'";

            ResultSet rs = statement.executeQuery(sqlQuery);

            File file = new File("resources/files/presencasAdmin.csv");

            String[] dateFormat = rs.getString("Data").split("/");
            String dia = dateFormat[0];
            String mes = dateFormat[1];
            String ano = dateFormat[2];

            String[] hourFormat = rs.getString("HoraInicio").split(":");
            String hora = hourFormat[0];
            String minutos = hourFormat[1];

            try (FileWriter csvWriter = new FileWriter(file)) { // meter o while()
                csvWriter.append("Nome").append(";").append(rs.getString("Nome")).append("\n")
                        .append("Local").append(";").append(rs.getString("Local")).append("\n")
                        .append("Data").append(";").append(dia).append(";").append(mes).append(";").append(ano).append("\n")
                        .append("HoraInicio").append(";").append(hora).append(";").append(minutos).append("\n\n\n");

                csvWriter.append("Nome").append(";").append("Número identificação").append(";").append("Email").append("\n");

                String sqlQueryUserInfo = "SELECT utilizador.Nome, NIF, Email FROM Utilizador utilizador " +
                        "JOIN Presenca presenca ON presenca.IdUtilizador = utilizador.Id " +
                        "JOIN Evento evento ON presenca.IdEvento = evento.Id " +
                        "WHERE evento.Id='" + eventId + "'";

                ResultSet rs1 = statement.executeQuery(sqlQueryUserInfo);

                while (rs1.next()) {
                    csvWriter.append(rs1.getString("Nome")).append(";").append(rs1.getString("NIF")).append(";").append(rs1.getString("Email")).append("\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public String checkCreatedEvents(String pesquisa) {
        Statement statement = null;

        try {
            statement = conn.createStatement();

            String titleCard = "Nome\t\tLocal\t\tData\t\tHoraInicio\t\tHoraFim\n";

            if (pesquisa.equals("")) {
                String sqlQueryAllEvents = "SELECT * FROM Evento";

                ResultSet resultSet = statement.executeQuery(sqlQueryAllEvents);

                StringBuilder ret = new StringBuilder();
                ret.append(titleCard);

                while(resultSet.next()){
                    ret.append(resultSet.getString("Nome")).append(" ").append(resultSet.getString("Local")).append(" ").append(resultSet.getString("Data")).append(" ").append(resultSet.getString("HoraInicio")).append(" ").append(resultSet.getString("HoraFim")).append("\n");
                }

                return ret.toString();
            }

            String sqlQuery = "SELECT Nome, Local, Data, HoraInicio, HoraFim FROM Evento WHERE lower(Nome) LIKE lower('" + pesquisa + "') OR lower(Local) LIKE lower('" + pesquisa + "') OR lower(Data) LIKE lower('" + pesquisa + "') OR lower(HoraInicio) LIKE lower('" + pesquisa + "') OR lower(HoraFim) LIKE lower('" + pesquisa + "')";

            ResultSet rs = statement.executeQuery(sqlQuery);

            StringBuilder returnValue = new StringBuilder();
            returnValue.append(titleCard);

            while (rs.next()) {
                returnValue.append(rs.getString("Nome")).append(" ").append(rs.getString("Local")).append(" ").append(rs.getString("Data")).append(" ").append(rs.getString("HoraInicio")).append(" ").append(rs.getString("HoraFim")).append("\n");
            }

            return returnValue.toString();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return "";
    }

    public boolean getCSVAdminListUserAttendanceByEmail(String email) {
        Statement statement = null;

        try {
            statement = conn.createStatement();

            statement.execute("PRAGMA header = on");
            statement.execute("PRAGMA mode = csv");

            String userQuery = "SELECT Id, Nome, NIF, Email FROM Utilizador WHERE Email='" + email + "'";

            ResultSet userResult = statement.executeQuery(userQuery);

            String eventQuery = "SELECT Evento.Nome as nomeEvento, Evento.Local, Evento.Data, Evento.HoraInicio FROM Evento evento " +
                    "JOIN Presenca presenca ON evento.Id = presenca.IdEvento " +
                    "JOIN Utilizador utilizador ON utilizador.Id = presenca.IdUtilizador " +
                    "WHERE utilizador.Id='" + userResult.getInt("Id") + "'";

            File file = new File("resources/files/presencasUserByEmailAdmin.csv");

            try (FileWriter csvWriter = new FileWriter(file)) {
                csvWriter.append("NomeCliente,NIF,Email\n");

                //while (userResult.next()) {
                csvWriter.append(userResult.getString("Nome"))
                        .append(",")
                        .append(userResult.getString("NIF"))
                        .append(",")
                        .append(userResult.getString("Email"))
                        .append("\n\n");
                //}

                csvWriter.append("NomeEvento, Local, Data, Hora Inicio\n");

                ResultSet eventResult = statement.executeQuery(eventQuery);

                while (eventResult.next()) {
                    csvWriter
                            .append(eventResult.getString("NomeEvento"))
                            .append(",")
                            .append(eventResult.getString("Local"))
                            .append(",")
                            .append(eventResult.getString("Data"))
                            .append(",")
                            .append(eventResult.getString("HoraInicio"))
                            .append("\n");
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return false;
    }

    public String checkAllRegisteredPresences(int eventId) {
        Statement statement = null;

        try {

            statement = conn.createStatement();

            String sqlQuery = "SELECT utilizador.Nome, NIF, Email FROM Utilizador utilizador " +
                    "JOIN Presenca presenca ON presenca.IdUtilizador = utilizador.Id " +
                    "JOIN Evento evento ON presenca.IdEvento = evento.Id " +
                    "WHERE evento.Id='" + eventId + "'";

            ResultSet rs = statement.executeQuery(sqlQuery);

            StringBuilder sb = new StringBuilder();

            String titleCard = "Nome\t\tNIF\t\tEmail\n";

            sb.append(titleCard);

            while (rs.next()) {
                sb.append(rs.getString("Nome")).append("\t").append(rs.getString("NIF")).append("\t").append(rs.getString("Email")).append("\n");
            }

            return sb.toString();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return "";
    }

    public boolean removeUsersOnEventEnd() {
        Statement statement = null;

        try {
            statement = conn.createStatement();

            String horaAtual = String.valueOf(LocalTime.now().getHour());
            String minutoAtual = String.valueOf(LocalTime.now().getMinute());

            String query = "SELECT Id, Data, HoraFim FROM Evento";
            ResultSet queryResult = statement.executeQuery(query);

            while(queryResult.next()){

                String data = queryResult.getString("Data");
                String horaFim = queryResult.getString("HoraFim");

                String[] splitData = data.split("/");
                int diaDB = Integer.parseInt(splitData[0]);
                int mesDB = Integer.parseInt(splitData[1]);
                int anoDB = Integer.parseInt(splitData[2]);

                Thread.sleep(1000);

                String[] splitHoraFim = horaFim.split(":");
                String horaFimDB = splitHoraFim[0];
                String minutoFimDB = splitHoraFim[1];

                if(diaDB == LocalDate.now().getDayOfMonth() && mesDB == LocalDate.now().getMonthValue() && anoDB == LocalDate.now().getYear()) {
                    if(Integer.parseInt(minutoFimDB) == 59 && Integer.parseInt(minutoAtual) == 0) {
                        String sqlRemoveQuery = "DELETE FROM Presenca " +
                                "WHERE IdUtilizador IN (SELECT Id FROM Utilizador WHERE Id = Presenca.IdUtilizador) " +
                                "AND IdEvento IN (SELECT Id FROM Evento WHERE Id = '" + queryResult.getString("Id") + "')";

                        statement.executeUpdate(sqlRemoveQuery);
                        updateDBVersion();
                        this.executedQuery = sqlRemoveQuery;
                        return true;
                    }

                    if(Integer.parseInt(horaFimDB) <= Integer.parseInt(horaAtual) && Integer.parseInt(minutoFimDB) <= Integer.parseInt(minutoAtual)){
                        String sqlRemoveQuery = "DELETE FROM Presenca " +
                                "WHERE IdUtilizador IN (SELECT Id FROM Utilizador WHERE Id = Presenca.IdUtilizador) " +
                                "AND IdEvento IN (SELECT Id FROM Evento WHERE Id = '" + queryResult.getString("Id") + "')";

                        statement.executeUpdate(sqlRemoveQuery);
                        updateDBVersion();
                        this.executedQuery = sqlRemoveQuery;
                        return true;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return false;
    }

    public void setExecutedQuery(String query){
        this.executedQuery = query;
    }

    public String getExecutedQuery(){
        return this.executedQuery;
    }
}


