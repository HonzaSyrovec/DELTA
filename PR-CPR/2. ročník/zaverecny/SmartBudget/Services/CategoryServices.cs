using SmartBudget.Data;
using SmartBudget.Models;

namespace SmartBudget.Services
{
    public class CategoryService
    {
        private readonly AppDbContext _db;

        public CategoryService(AppDbContext db)
        {
            _db = db;
        }

        // Načtení kategorií uživatele
        public List<Category> GetCategories(int userId)
        {
            return _db.Categories
                .Where(c => c.UserId == userId)
                .OrderBy(c => c.Name)
                .ToList();
        }

        // Přidání nové kategorie
        public bool AddCategory(int userId, string name)
        {
            if (string.IsNullOrWhiteSpace(name)) return false;
            if (_db.Categories.Any(c => c.UserId == userId && c.Name == name)) return false;

            _db.Categories.Add(new Category
            {
                UserId = userId,
                Name = name
            });
            _db.SaveChanges();
            return true;
        }

        // Smazání kategorie
        public void DeleteCategory(int categoryId)
        {
            var category = _db.Categories.Find(categoryId);
            if (category != null)
            {
                _db.Categories.Remove(category);
                _db.SaveChanges();
            }
        }
    }
}