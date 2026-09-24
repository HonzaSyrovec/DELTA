using Microsoft.EntityFrameworkCore;
using SmartBudget.Models;
using System.IO;

namespace SmartBudget.Data
{
    public class AppDbContext : DbContext
    {
        public DbSet<User> Users { get; set; }
        public DbSet<Transaction> Transactions { get; set; }
        public DbSet<Category> Categories { get; set; }

        // Běžný konstruktor – používá se v aplikaci, beze změny chování
        public AppDbContext() { }

        // Nový konstruktor – použijí ho jen unit testy (předají vlastní in-memory databázi)
        public AppDbContext(DbContextOptions<AppDbContext> options) : base(options) { }

        protected override void OnConfiguring(DbContextOptionsBuilder options)
        {
            if (!options.IsConfigured)
            {
                string dbPath = Path.Combine(
                    AppDomain.CurrentDomain.BaseDirectory,
                    "smartbudget.db"
                );
                options.UseSqlite($"Data Source={dbPath}");
            }
        }

        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            modelBuilder.Entity<User>()
                .HasIndex(u => u.Username)
                .IsUnique();

            modelBuilder.Entity<Transaction>()
                .Property(t => t.Amount)
                .HasColumnType("decimal(18,2)");

            modelBuilder.Entity<Category>()
                .Property(c => c.MonthlyLimit)
                .HasColumnType("decimal(18,2)");
        }
    }
}