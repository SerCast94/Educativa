<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="html" indent="yes" />

    <xsl:key name="horas" match="entrada" use="tramo_horario" />

    <xsl:template match="/horario">
        <html>
            <head>
                <title>Horario de Clases</title>
                <style>
                    table {
                    width: 100%;
                    border-collapse: collapse;
                    font-family: 'Arial', sans-serif;
                    background-color: #f9f9f9;
                    box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
                    }

                    th, td {
                    padding: 12px 20px;
                    text-align: center;
                    border: 1px solid #ddd;
                    font-size: 16px;
                    }

                    th {
                    background-color: #4CAF50;
                    color: white;
                    font-weight: bold;
                    text-transform: uppercase;
                    }

                    td {
                    background-color: #ffffff;
                    color: #333;
                    border-radius: 8px;
                    }

                    td.asignatura-celda {
                    transition: background-color 0.3s, transform 0.3s ease;
                    }

                    td.asignatura-celda:hover {
                    background-color: #f1f1f1;
                    transform: scale(1.05);
                    }

                    tr:nth-child(even) td {
                    background-color: #f2f2f2;
                    }

                    h1 {
                    text-align: center;
                    color: #333;
                    font-family: 'Roboto', sans-serif;
                    font-size: 2rem;
                    margin-bottom: 20px;
                    }

                    body {
                    font-family: 'Arial', sans-serif;
                    background-color: #f4f4f9;
                    margin: 0;
                    padding: 20px;
                    }
                </style>
                <script>
                    function obtenerColoresUnicos(asignaturas) {
                    const coloresDisponibles = [
                    '#34a853', '#4a86e8', '#ff9900', '#ff0000',
                    '#ff0099', '#674ea7', '#ffff00', '#00ff00', '#0000ff'
                    ];
                    let asignacionColores = {};
                    let usados = new Set();
                    let index = 0;

                    asignaturas.forEach(nombre => {
                    while (usados.has(coloresDisponibles[index])) {
                    index = (index + 1) % coloresDisponibles.length;
                    }
                    asignacionColores[nombre] = coloresDisponibles[index];
                    usados.add(coloresDisponibles[index]);
                    });
                    return asignacionColores;
                    }

                    function asignarColores() {
                    let asignaturas = new Set();
                    document.querySelectorAll('.asignatura').forEach(elemento => {
                    asignaturas.add(elemento.textContent.trim());
                    });
                    let colores = obtenerColoresUnicos(Array.from(asignaturas));

                    document.querySelectorAll('.asignatura').forEach(elemento => {
                    let nombre = elemento.textContent.trim();
                    let celda = elemento.closest('td');
                    let color = colores[nombre];
                    celda.style.backgroundColor = color;
                    celda.style.color = '#000000';
                    });
                    }

                    window.onload = asignarColores;
                </script>
            </head>
            <body>
                <h1>Horario de Clases</h1>
                <table>
                    <thead>
                        <tr>
                            <th>Hora</th>
                            <th>Lunes</th>
                            <th>Martes</th>
                            <th>Miércoles</th>
                            <th>Jueves</th>
                            <th>Viernes</th>
                        </tr>
                    </thead>
                    <tbody>
                        <xsl:for-each select="entrada[generate-id() = generate-id(key('horas', tramo_horario)[1])]">
                            <xsl:sort select="tramo_horario" />
                            <tr>
                                <td><xsl:value-of select="tramo_horario" /></td>
                                <xsl:call-template name="dia">
                                    <xsl:with-param name="dia" select="'lunes'" />
                                    <xsl:with-param name="tramo" select="tramo_horario" />
                                </xsl:call-template>
                                <xsl:call-template name="dia">
                                    <xsl:with-param name="dia" select="'martes'" />
                                    <xsl:with-param name="tramo" select="tramo_horario" />
                                </xsl:call-template>
                                <xsl:call-template name="dia">
                                    <xsl:with-param name="dia" select="'miercoles'" />
                                    <xsl:with-param name="tramo" select="tramo_horario" />
                                </xsl:call-template>
                                <xsl:call-template name="dia">
                                    <xsl:with-param name="dia" select="'jueves'" />
                                    <xsl:with-param name="tramo" select="tramo_horario" />
                                </xsl:call-template>
                                <xsl:call-template name="dia">
                                    <xsl:with-param name="dia" select="'viernes'" />
                                    <xsl:with-param name="tramo" select="tramo_horario" />
                                </xsl:call-template>
                            </tr>
                        </xsl:for-each>
                    </tbody>
                </table>
            </body>
        </html>
    </xsl:template>

    <xsl:template name="dia">
        <xsl:param name="dia" />
        <xsl:param name="tramo" />
        <xsl:variable name="entradas" select="/horario/entrada[translate(dia, 'ABCDEFGHIJKLMNOPQRSTUVWXYZÁÉÍÓÚ', 'abcdefghijklmnopqrstuvwxyzáéíóú') = $dia and tramo_horario = $tramo]" />
        <xsl:choose>
            <xsl:when test="$entradas">
                <td class="asignatura-celda">
                    <xsl:for-each select="$entradas">
                        <span class="asignatura">
                            <xsl:value-of select="asignatura" />
                        </span>
                        <br />
                        <xsl:value-of select="profesor" />
                    </xsl:for-each>
                </td>
            </xsl:when>
            <xsl:otherwise>
                <td>Libre</td>
            </xsl:otherwise>
        </xsl:choose>
    </xsl:template>
</xsl:stylesheet>
