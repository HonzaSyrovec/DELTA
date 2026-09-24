using MahApps.Metro.Controls;
using System.IO;
using System.Text;
using System.Text.Json;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Navigation;
using System.Windows.Shapes;

namespace EvidenceStudentu
{
    /// <summary>
    /// Interaction logic for MainWindow.xaml
    /// </summary>
   
    public partial class MainWindow : MetroWindow
    {
        static List<Student> seznamStudentu = new List<Student>();
        public MainWindow()
        {
            InitializeComponent();
        }

        private void Button_Click(object sender, RoutedEventArgs e)
        {
            string j, p;
            int v;
            j = txtjmeno.Text;
            p = txtprijmeni.Text;
            v = int.Parse(txtvek.Text);
            Student s = new Student(j, p, v);
            
            seznamStudentu.Add(s);
            var items = seznamStudentu;
            lstbox.ItemsSource = null;
            lstbox.ItemsSource = items;
           
            //txbZobrazeni.Text = s.ToString();
        }

        private void lstboxChanged(object sender, SelectionChangedEventArgs e)
        {
            var selected = lstbox.SelectedItem as Student;

            if (selected != null)
            {
                //MessageBox.Show(selected.Prijmeni);
                txtjmeno.Text = selected.Jmeno;
                txtprijmeni.Text = selected.Prijmeni;
                txtvek.Text = "" + selected.Vek;
            }
        }

        private void Button_Click_1(object sender, RoutedEventArgs e)
        {
            if (lstbox.SelectedItem is Student vybrany)
            {
                vybrany.Jmeno = txtjmeno.Text;
                vybrany.Prijmeni = txtprijmeni.Text;
                vybrany.Vek = int.Parse(txtvek.Text);

                // refresh ListBoxu
                lstbox.ItemsSource = null;
                lstbox.ItemsSource = seznamStudentu;
            }
        }

        private void Button_Click_2(object sender, RoutedEventArgs e)
        {
            string cesta = "studenti.json";
          //  MessageBox.Show(Environment.CurrentDirectory);

            if (!File.Exists(cesta))
            {
                MessageBox.Show("Soubor nebyl nalezen.");
                return;
            }

            string json = File.ReadAllText(cesta);
            var options = new JsonSerializerOptions
            {
                PropertyNameCaseInsensitive = true
            };

            seznamStudentu = JsonSerializer.Deserialize<List<Student>>(json, options);
           

            MessageBox.Show($"Načteno studentů: {seznamStudentu.Count}");
            lstbox.ItemsSource = null;
            lstbox.ItemsSource = seznamStudentu;
        }
    }
}