using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using TomaDePedido.Models;

namespace TomaDePedido
{
    public partial class InterfazPedido : Form
    {
        public InterfazPedido()
        {
            InitializeComponent();
        }

        private void button1_Click(object sender, EventArgs e)
        {
            var gestorPedido = new Gestores.GestorPedido();

            var pedido = new Pedido(14) { EntregarTodoJunto = false };
            pedido.AgregarCerveza(22, 3);
            pedido.AgregarCerveza(23, 1);
            pedido.AgregarPlato(2, 1, "Sin sal");
            pedido.AgregarPlato(3, 1, string.Empty);

            gestorPedido.EnviarPedido(pedido);
        }

        private void button2_Click(object sender, EventArgs e)
        {
            var gestorPedido = new Gestores.GestorPedido();
            var codigoMesa = 14;
            var mesa = gestorPedido.ObtenerPedidos(codigoMesa);
        }
    }
}
