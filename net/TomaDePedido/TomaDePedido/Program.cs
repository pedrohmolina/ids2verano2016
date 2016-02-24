namespace TomaDePedido
{
    using System;
    using System.Windows.Forms;

    static class Program
    {
        /// <summary>
        /// The main entry point for the application.
        /// </summary>
        [STAThread]
        static void Main()
        {
            Application.EnableVisualStyles();
            Application.SetCompatibleTextRenderingDefault(false);
            Application.Run(new InterfazPedido());


            //var codigoMesa = 4;
            //var gestorPedido = new Gestores.GestorPedido();
            //var mesa = gestorPedido.ObtenerMesa(codigoMesa);


            //var pedido = new Pedido(codigoMesa) { EntregarTodoJunto = false };
            //pedido.AgregarCerveza(22, 3);
            //pedido.AgregarCerveza(23, 1);
            //pedido.AgregarPlato(2, 1, "Sin sal");
            //pedido.AgregarPlato(3, 1, string.Empty);
            //gestorPedido.EnviarPedido(pedido);

            //var pedidoRecuperado = gestorPedido.ObtenerPedidos(codigoMesa);

            //var mesas = gestorPedido.ObtenerMesas();
        }
    }
}
