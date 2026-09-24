using SmartBudget.ViewModels;
using System.Windows;

namespace SmartBudget.Views
{
    public partial class LoginWindow : Window
    {
        private readonly LoginViewModel _vm;

        public LoginWindow()
        {
            InitializeComponent();
            _vm = new LoginViewModel();
            DataContext = _vm;

            _vm.OnLoginSuccess = user =>
            {
                var mainWindow = new MainWindow(user);
                mainWindow.Show();
                this.Close();
            };

            _vm.OnNavigateToRegister = () =>
            {
                var registerWindow = new RegisterWindow();
                registerWindow.Show();
                this.Close();
            };
        }

        private void LoginButton_Click(object sender, RoutedEventArgs e)
        {
            _vm.LoginCommand.Execute(PasswordBox.Password);
        }
    }
}