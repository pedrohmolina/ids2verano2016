namespace Test
{
    using Moq;
    using Microsoft.VisualStudio.TestTools.UnitTesting;
    using TomaDePedido.Models;
    using TomaDePedido.Interfaces;
    using TomaDePedido.Enums;
    using TomaDePedido.Gestores;
    using System.Collections.Generic;

    [TestClass]
    public class MesaTest
    {
        GestorPedido gestorPedido;
        Mock<IGestorFacturacion> gestorFacturacion;
        Mock<IGestorCocina> gestorCocina;
        Mock<IGestorStock> gestorStock;

        [TestInitialize]
        public void TestInitialize()
        {
            this.gestorFacturacion = new Mock<IGestorFacturacion>();
            this.gestorCocina = new Mock<IGestorCocina>();
            this.gestorStock = new Mock<IGestorStock>();
        }

        [TestMethod]
        public void ObtenerMontoAPagarTest()
        {
            var codigoMesa = 72;
            gestorFacturacion.Setup(f => f.ObtenerSaldoMesa(codigoMesa)).Returns(400);
            this.gestorPedido = new GestorPedido(gestorCocina.Object, gestorFacturacion.Object, gestorStock.Object);
            Assert.AreEqual(145.50, this.gestorPedido.ObtenerSaldoMesa(codigoMesa));                        
        }

        [TestMethod]
        public void DebeRetornarEstadoDeMesaOcupada()
        {
            var codigoMesa = 72;
            gestorFacturacion.Setup(f => f.ObtenerEstadoMesa(codigoMesa)).Returns((int)Enums.EstadoMesa.Ocupada);
            this.gestorPedido = new GestorPedido(gestorCocina.Object, gestorFacturacion.Object, gestorStock.Object);

            this.gestorPedido.OcuparMesa(codigoMesa);          
            Assert.AreEqual(this.gestorPedido.ObtenerEstadoMesa(codigoMesa), Enums.EstadoMesa.Ocupada);
        }

        [TestMethod]
        public void DebeRetornarCorrectamente3Mesas()
        {
            var mesas = new List<IMesa>()
            {
                new Mesa() { CodigoMesa = 1, Nombre = "Paulaner", Estado = Enums.EstadoMesa.Libre },
                new Mesa() { CodigoMesa = 2, Nombre = "Oranjeboom", Estado = Enums.EstadoMesa.Ocupada },
                new Mesa() { CodigoMesa = 3, Nombre = "Trappist", Estado = Enums.EstadoMesa.Ocupada },
            };

            gestorFacturacion.Setup(f => f.ObtenerMesas()).Returns(mesas);
            Assert.AreEqual(this.gestorPedido.ObtenerMesas().Count, 3);
        }

        [TestMethod]
        public void DebeRetornarEstadoDeMesaLibre()
        {
            var codigoMesa = 72;
            gestorFacturacion.Setup(f => f.ObtenerEstadoMesa(codigoMesa)).Returns((int)Enums.EstadoMesa.Libre);
            this.gestorPedido = new GestorPedido(gestorCocina.Object, gestorFacturacion.Object, gestorStock.Object);

            this.gestorPedido.LiberarMesa(codigoMesa);
            Assert.AreEqual(this.gestorPedido.ObtenerEstadoMesa(codigoMesa), Enums.EstadoMesa.Libre);
        }
    }
}
