using SmartBudget.Helpers;
using SmartBudget.Models;
using SmartBudget.ViewModels;
using System.Windows;

namespace SmartBudget
{
    public partial class MainWindow : Window
    {
        public MainWindow(User user)
        {
            InitializeComponent();
            var vm = new MainViewModel(user);
            DataContext = vm;

            vm.OnLogout = () =>
            {
                var loginWindow = new Views.LoginWindow();
                loginWindow.Show();
                this.Close();
            };
        }
    
        private void ToggleTheme_Click(object sender, RoutedEventArgs e)
        {
            ThemeService.Toggle();
            var btn = sender as System.Windows.Controls.Button;
            if (btn != null)
                btn.Content = ThemeService.IsDark ? "☀️ Světlý režim" : "🌙 Tmavý režim";
        }
    }
}