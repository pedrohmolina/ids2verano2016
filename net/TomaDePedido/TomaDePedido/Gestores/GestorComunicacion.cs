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
        private string url = "http://localhost/yourwebapi";
        
        /// <summary>
        /// Realiza la comunicación con el Servicio de Facturación para obtener el estado de una mesa a partir de su codigo
        /// </summary>
        /// <param name="codigoMesa">Codigo de mesa</param>
        /// <returns>Un entero representando el estado de una mesa</returns>
        public int ObtenerEstadoMesa(int codigoMesa)
        {
            return 0;
        }

        public List<IPedido> ObtenerPedidos(int codigoMesa)
        {
            this.url = "http://localhost/mesas/" + codigoMesa;
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
                var pedidos = serializer.Deserialize<List<IPedido>>(result);
                return pedidos;
            }
            catch (WebException ex)
            {
                throw ex;
            }
        }

        public void EnviarPedido(IPedido pedido)
        {
            HttpWebRequest request = (HttpWebRequest)WebRequest.Create(url);
            request.Method = "POST";

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
                // Log exception and throw as for GET example above
            }
        }
    }
}
