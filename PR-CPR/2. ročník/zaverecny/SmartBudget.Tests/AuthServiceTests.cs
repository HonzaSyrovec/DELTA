using SmartBudget.Services;

namespace SmartBudget.Tests
{
    public class AuthServiceTests
    {
        [Fact] // F001
        public void Register_NewUser_ReturnsTrueAndSavesUser()
        {
            var db = TestDbFactory.CreateInMemoryContext();
            var auth = new AuthService(db);

            bool result = auth.Register("petr", "heslo123");

            Assert.True(result);
            Assert.Single(db.Users);
        }

        [Fact] // F001
        public void Register_DuplicateUsername_ReturnsFalse()
        {
            var db = TestDbFactory.CreateInMemoryContext();
            var auth = new AuthService(db);

            auth.Register("petr", "heslo123");
            bool result = auth.Register("petr", "jinaheslo");

            Assert.False(result);
        }
        [Fact] // F002
        public void Login_CorrectCredentials_ReturnsUser()
        {
            var db = TestDbFactory.CreateInMemoryContext();
            var auth = new AuthService(db);
            auth.Register("petr", "heslo123");

            var user = auth.Login("petr", "heslo123");

            Assert.NotNull(user);
            Assert.Equal("petr", user!.Username);
        }

        [Fact] // F002
        public void Login_WrongPassword_ReturnsNull()
        {
            var db = TestDbFactory.CreateInMemoryContext();
            var auth = new AuthService(db);
            auth.Register("petr", "heslo123");

            var user = auth.Login("petr", "spatneheslo");

            Assert.Null(user);
        }
    }
}