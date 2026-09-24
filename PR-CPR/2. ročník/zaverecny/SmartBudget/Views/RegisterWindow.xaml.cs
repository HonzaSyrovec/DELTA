using SmartBudget.ViewModels;
using System.Windows;

namespace SmartBudget.Views
{
    public partial class RegisterWindow : Window
    {
        private readonly RegisterViewModel _vm;

        public RegisterWindow()
        {
            InitializeComponent();
            _vm = new RegisterViewModel();
            DataContext = _vm;

            _vm.OnNavigateToLogin = () =>
            {
                var loginWindow = new LoginWindow();
                loginWindow.Show();
                this.Close();
            };
        }

        private void RegisterButton_Click(object sender, RoutedEventArgs e)
        {
            _vm.RegisterCommand.Execute(PasswordBox.Password);
        }
    }
}