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
using System.Web.Script.Serialization;

namespace TomaDePedido
{
    public partial class InterfazPedido : Form
    {
        private Gestores.GestorPedido gestorPedido;
        private JavaScriptSerializer serializer;
        private int codigoMesa;

        public InterfazPedido()
        {
            InitializeComponent();
            this.gestorPedido = new Gestores.GestorPedido();
            this.serializer = new JavaScriptSerializer();
        }

        private void button1_Click(object sender, EventArgs e)
        {
            var codigoPlato = new Random().Next(0, 20);
            var codigoCerveza = new Random().Next(0, 20);
            var pedido = new Pedido(this.codigoMesa) { EntregarTodoJunto = false };
            pedido.AgregarCerveza(codigoCerveza, 3);
            pedido.AgregarPlato(codigoPlato, 1, "Sin sal");

            this.gestorPedido.EnviarPedido(pedido);
            ShowResult("Pedido enviado wachin!!!!");
        }

        private void button2_Click(object sender, EventArgs e)
        {
            var mesa = this.gestorPedido.ObtenerPedidos(this.codigoMesa);
            ShowResult(mesa);
        }

        private void ShowResult(object obj)
        {
            textAreaResult.Clear();
            textAreaResult.Text = this.serializer.Serialize(obj);
        }

        private void button3_Click(object sender, EventArgs e)
        {
            var mesas = this.gestorPedido.ObtenerMesas();
            ShowResult(mesas);
        }

        private void label1_Click(object sender, EventArgs e)
        {

        }

        private void numerosMesas_ValueChanged(object sender, EventArgs e)
        {
            this.codigoMesa = (int)numerosMesas.Value;
        }

        private void button4_Click(object sender, EventArgs e)
        {
            var mesa = this.gestorPedido.ObtenerMesa(this.codigoMesa);
            ShowResult(mesa);
        }

        private void button5_Click(object sender, EventArgs e)
        {
            var saldo = this.gestorPedido.PedirCuenta(this.codigoMesa);
            ShowResult(saldo);
        }
    }
}
