using BCrypt.Net;
using SmartBudget.Data;
using SmartBudget.Models;

namespace SmartBudget.Services
{
    public class AuthService
    {
        private readonly AppDbContext _db;

        public AuthService(AppDbContext db)
        {
            _db = db;
        }

        // Registrace nového uživatele (F001)
        public bool Register(string username, string password)
        {
            // Zkontroluj jestli username už existuje
            if (_db.Users.Any(u => u.Username == username))
                return false;

            var user = new User
            {
                Username = username,
                PasswordHash = BCrypt.Net.BCrypt.HashPassword(password)  // N003 - hashování
            };

            _db.Users.Add(user);
            _db.SaveChanges();
            return true;
        }

        // Přihlášení uživatele (F002)
        public User? Login(string username, string password)
        {
            var user = _db.Users.FirstOrDefault(u => u.Username == username);
            if (user == null) return null;

            bool passwordOk = BCrypt.Net.BCrypt.Verify(password, user.PasswordHash);
            return passwordOk ? user : null;
        }
    }
}