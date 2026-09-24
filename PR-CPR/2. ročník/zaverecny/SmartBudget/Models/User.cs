namespace SmartBudget.Models
{
    public class User
    {
        public int Id { get; set; }
        public string Username { get; set; } = "";
        public string PasswordHash { get; set; } = "";

        // Navigační vlastnost — jeden uživatel má více transakcí
        public List<Transaction> Transactions { get; set; } = new();
        public List<Category> Categories { get; set; } = new();
    }
}