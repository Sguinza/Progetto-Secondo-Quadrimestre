public class GestioneDB
{

	private Connection con;
    private Statement stmt;
    private ResultSet rs;

	public GestioneDB()
	{
		Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/progetto", "root", "");
        stmt = con.createStatement();
	}


	public ResultSet Registra()
	{

	}

	public ResultSet Login()
	{

	}


	


}