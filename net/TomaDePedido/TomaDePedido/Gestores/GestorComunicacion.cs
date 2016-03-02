//-----------------------------------------------------------------------
// <copyright file="GestorComunicacion.cs" company="CAECE ENTERPRAISSSSS">
//     Copyright (c) Caece Enterpraisssss. All rights reserved.
// </copyright>
//-----------------------------------------------------------------------

namespace TomaDePedido.Gestores
{
    using System;
    using System.Collections.Generic;
    using System.IO;
    using System.Net;
    using System.Net.Http;
    using System.Net.Http.Headers;
    using System.Text;
    using Models;
    using TomaDePedido.Interfaces;

    /// <summary>
    /// Gestiona la comunicacion via Web.Api
    /// </summary>
    public class GestorComunicacion : IGestorComunicacion
    {
        /// <summary>
        /// Dirección del servicio webApi
        /// </summary>
        private string baseUrl = "http://192.168.0.131:8080";
        
        /// <summary>
        /// Realiza la comunicación con el Servicio de Facturación para obtener el estado de una mesa a partir de su codigo
        /// </summary>
        /// <param name="codigoMesa">Codigo de mesa</param>
        /// <returns>Un entero representando el estado de una mesa</returns>
        public int ObtenerEstadoMesa(int codigoMesa)
        {
            return 0;
        }

        public Mesa ObtenerMesa(int codigoMesa)
        {
            var url = baseUrl + "/mesas/" + codigoMesa;
            var result = string.Empty;

            HttpWebRequest request = (HttpWebRequest)WebRequest.Create(url);
            try
            {
                WebResponse response = request.GetResponse();
                using (Stream responseStream = response.GetResponseStream())
                {
                    StreamReader reader = new StreamReader(responseStream, Encoding.UTF8);
                    result = reader.ReadToEnd();
                }

                System.Web.Script.Serialization.JavaScriptSerializer serializer = new System.Web.Script.Serialization.JavaScriptSerializer();
                var mesa = serializer.Deserialize<Mesa>(result);
                return mesa;
            }
            catch (WebException ex)
            {
                throw ex;
            }
        }

        public long ObtenerSaldoMesa(int codigoMesa)
        {
            var url = baseUrl + "/mesas/" + codigoMesa + "/cuenta";
            var result = string.Empty;

            HttpWebRequest request = (HttpWebRequest)WebRequest.Create(url);
            try
            {
                WebResponse response = request.GetResponse();
                using (Stream responseStream = response.GetResponseStream())
                {
                    StreamReader reader = new StreamReader(responseStream, Encoding.UTF8);
                    result = reader.ReadToEnd();
                }

                System.Web.Script.Serialization.JavaScriptSerializer serializer = new System.Web.Script.Serialization.JavaScriptSerializer();
                var mesa = serializer.Deserialize<Mesa>(result);
                return mesa.Total;
            }
            catch (WebException ex)
            {
                throw ex;
            }
        }


        public List<Pedido> ObtenerPedidos(int codigoMesa)
        {
            var url = baseUrl + "/mesas/" + codigoMesa + "/pedidos";
            var result = string.Empty;

            HttpWebRequest request = (HttpWebRequest)WebRequest.Create(url);
            try
            {
                WebResponse response = request.GetResponse();
                using (Stream responseStream = response.GetResponseStream())
                {
                    StreamReader reader = new StreamReader(responseStream, Encoding.UTF8);
                    result = reader.ReadToEnd();
                }

                System.Web.Script.Serialization.JavaScriptSerializer serializer = new System.Web.Script.Serialization.JavaScriptSerializer();
                var pedidos = serializer.Deserialize<List<Pedido>>(result);
                return pedidos;
            }
            catch (WebException ex)
            {
                throw ex;
            }
        }

        public List<Mesa> ObtenerMesas()
        {
            var url = baseUrl + "/mesas";
            var result = string.Empty;

            HttpWebRequest request = (HttpWebRequest)WebRequest.Create(url);
            try
            {
                WebResponse response = request.GetResponse();
                using (Stream responseStream = response.GetResponseStream())
                {
                    StreamReader reader = new StreamReader(responseStream, Encoding.UTF8);
                    result = reader.ReadToEnd();
                }

                System.Web.Script.Serialization.JavaScriptSerializer serializer = new System.Web.Script.Serialization.JavaScriptSerializer();
                var mesas = serializer.Deserialize<List<Mesa>>(result);
                return mesas;
            }
            catch (WebException ex)
            {
                throw ex;
            }
        }

        public void EnviarPedido(IPedido pedido)
        {
            var url = baseUrl + "/procesarPedido";
            HttpWebRequest request = (HttpWebRequest)WebRequest.Create(url);
            
            request.Method = "PUT";

            System.Web.Script.Serialization.JavaScriptSerializer serializer = new System.Web.Script.Serialization.JavaScriptSerializer();
            string jsonString = serializer.Serialize(pedido);

            System.Text.UTF8Encoding encoding = new System.Text.UTF8Encoding();
            Byte[] byteArray = encoding.GetBytes(jsonString);

            request.ContentLength = byteArray.Length;
            request.ContentType = @"application/json";

            using (Stream dataStream = request.GetRequestStream())
            {
                dataStream.Write(byteArray, 0, byteArray.Length);
            }
            long length = 0;
            try
            {
                using (HttpWebResponse response = (HttpWebResponse)request.GetResponse())
                {
                    length = response.ContentLength;
                }
            }
            catch (WebException ex)
            {
                throw ex;
            }
        }
    }
}
