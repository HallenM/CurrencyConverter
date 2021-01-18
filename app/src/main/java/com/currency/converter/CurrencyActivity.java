package com.currency.converter;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;

import javax.net.ssl.HttpsURLConnection;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class CurrencyActivity extends AppCompatActivity {

    String xmlData = "<ValCurs Date=\"19.01.2021\" name=\"Foreign Currency Market\">" +
            "<Valute ID=\"R01010\">" +
            "<NumCode>036</NumCode>" +
            "<CharCode>AUD</CharCode>" +
            "<Nominal>1</Nominal>" +
            "<Name>Австралийский доллар</Name>" +
            "<Value>56,7525</Value>" +
            "</Valute>" +
            "<Valute ID=\"R01020A\">" +
            "<NumCode>944</NumCode>" +
            "<CharCode>AZN</CharCode>" +
            "<Nominal>1</Nominal>" +
            "<Name>Азербайджанский манат</Name>" +
            "<Value>43,5394</Value>" +
            "</Valute>" +
            "<Valute ID=\"R01035\">" +
            "<NumCode>826</NumCode>" +
            "<CharCode>GBP</CharCode>" +
            "<Nominal>1</Nominal>" +
            "<Name>Фунт стерлингов Соединенного королевства</Name>" +
            "<Value>100,1453</Value>" +
            "</Valute>" +
            "<Valute ID=\"R01060\">" +
            "<NumCode>051</NumCode>" +
            "<CharCode>AMD</CharCode>" +
            "<Nominal>100</Nominal>" +
            "<Name>Армянских драмов</Name>" +
            "<Value>14,1023</Value>" +
            "</Valute>" +
            "<Valute ID=\"R01090B\">" +
            "<NumCode>933</NumCode>" +
            "<CharCode>BYN</CharCode>" +
            "<Nominal>1</Nominal>" +
            "<Name>Белорусский рубль</Name>" +
            "<Value>28,9558</Value>" +
            "</Valute>" +
            "<Valute ID=\"R01100\">" +
            "<NumCode>975</NumCode>" +
            "<CharCode>BGN</CharCode>" +
            "<Nominal>1</Nominal>" +
            "<Name>Болгарский лев</Name>" +
            "<Value>45,6542</Value>" +
            "</Valute>" +
            "<Valute ID=\"R01115\">" +
            "<NumCode>986</NumCode>" +
            "<CharCode>BRL</CharCode>" +
            "<Nominal>1</Nominal>" +
            "<Name>Бразильский реал</Name>" +
            "<Value>13,9757</Value>" +
            "</Valute>" +
            "<Valute ID=\"R01135\">" +
            "<NumCode>348</NumCode>" +
            "<CharCode>HUF</CharCode>" +
            "<Nominal>100</Nominal>" +
            "<Name>Венгерских форинтов</Name>" +
            "<Value>24,7341</Value>" +
            "</Valute>" +
            "<Valute ID=\"R01200\">" +
            "<NumCode>344</NumCode>" +
            "<CharCode>HKD</CharCode>" +
            "<Nominal>10</Nominal>" +
            "<Name>Гонконгских долларов</Name>" +
            "<Value>95,4103</Value>" +
            "</Valute>" +
            "<Valute ID=\"R01215\">" +
            "<NumCode>208</NumCode>" +
            "<CharCode>DKK</CharCode>" +
            "<Nominal>1</Nominal>" +
            "<Name>Датская крона</Name>" +
            "<Value>12,0034</Value>" +
            "</Valute>" +
            "<Valute ID=\"R01235\">" +
            "<NumCode>840</NumCode>" +
            "<CharCode>USD</CharCode>" +
            "<Nominal>1</Nominal>" +
            "<Name>Доллар США</Name>" +
            "<Value>73,9735</Value>" +
            "</Valute>" +
            "<Valute ID=\"R01239\">" +
            "<NumCode>978</NumCode>" +
            "<CharCode>EUR</CharCode>" +
            "<Nominal>1</Nominal>" +
            "<Name>Евро</Name>" +
            "<Value>89,3304</Value>" +
            "</Valute>" +
            "<Valute ID=\"R01270\">" +
            "<NumCode>356</NumCode>" +
            "<CharCode>INR</CharCode>" +
            "<Nominal>10</Nominal>" +
            "<Name>Индийских рупий</Name>" +
            "<Value>10,0961</Value>" +
            "</Valute>" +
            "<Valute ID=\"R01335\">" +
            "<NumCode>398</NumCode>" +
            "<CharCode>KZT</CharCode>" +
            "<Nominal>100</Nominal>" +
            "<Name>Казахстанских тенге</Name>" +
            "<Value>17,5839</Value>" +
            "</Valute>" +
            "<Valute ID=\"R01350\">" +
            "<NumCode>124</NumCode>" +
            "<CharCode>CAD</CharCode>" +
            "<Nominal>1</Nominal>" +
            "<Name>Канадский доллар</Name>" +
            "<Value>57,8279</Value>" +
            "</Valute>" +
            "<Valute ID=\"R01370\">" +
            "<NumCode>417</NumCode>" +
            "<CharCode>KGS</CharCode>" +
            "<Nominal>100</Nominal>" +
            "<Name>Киргизских сомов</Name>" +
            "<Value>87,5334</Value>" +
            "</Valute>" +
            "<Valute ID=\"R01375\">" +
            "<NumCode>156</NumCode>" +
            "<CharCode>CNY</CharCode>" +
            "<Nominal>1</Nominal>" +
            "<Name>Китайский юань</Name>" +
            "<Value>11,3907</Value>" +
            "</Valute>" +
            "<Valute ID=\"R01500\">" +
            "<NumCode>498</NumCode>" +
            "<CharCode>MDL</CharCode>" +
            "<Nominal>10</Nominal>" +
            "<Name>Молдавских леев</Name>" +
            "<Value>42,7716</Value>" +
            "</Valute>" +
            "<Valute ID=\"R01535\">" +
            "<NumCode>578</NumCode>" +
            "<CharCode>NOK</CharCode>" +
            "<Nominal>10</Nominal>" +
            "<Name>Норвежских крон</Name>" +
            "<Value>85,9917</Value>" +
            "</Valute>" +
            "<Valute ID=\"R01565\">" +
            "<NumCode>985</NumCode>" +
            "<CharCode>PLN</CharCode>" +
            "<Nominal>1</Nominal>" +
            "<Name>Польский злотый</Name>" +
            "<Value>19,6404</Value>" +
            "</Valute>" +
            "<Valute ID=\"R01585F\">" +
            "<NumCode>946</NumCode>" +
            "<CharCode>RON</CharCode>" +
            "<Nominal>1</Nominal>" +
            "<Name>Румынский лей</Name>" +
            "<Value>18,3198</Value>" +
            "</Valute>" +
            "<Valute ID=\"R01589\">" +
            "<NumCode>960</NumCode>" +
            "<CharCode>XDR</CharCode>" +
            "<Nominal>1</Nominal>" +
            "<Name>СДР (специальные права заимствования)</Name>" +
            "<Value>106,5514</Value>" +
            "</Valute>" +
            "<Valute ID=\"R01625\">" +
            "<NumCode>702</NumCode>" +
            "<CharCode>SGD</CharCode>" +
            "<Nominal>1</Nominal>" +
            "<Name>Сингапурский доллар</Name>" +
            "<Value>55,4774</Value>" +
            "</Valute>" +
            "<Valute ID=\"R01670\">" +
            "<NumCode>972</NumCode>" +
            "<CharCode>TJS</CharCode>" +
            "<Nominal>10</Nominal>" +
            "<Name>Таджикских сомони</Name>" +
            "<Value>64,9175</Value>" +
            "</Valute>" +
            "<Valute ID=\"R01700J\">" +
            "<NumCode>949</NumCode>" +
            "<CharCode>TRY</CharCode>" +
            "<Nominal>10</Nominal>" +
            "<Name>Турецких лир</Name>" +
            "<Value>98,3076</Value>" +
            "</Valute>" +
            "<Valute ID=\"R01710A\">" +
            "<NumCode>934</NumCode>" +
            "<CharCode>TMT</CharCode>" +
            "<Nominal>1</Nominal>" +
            "<Name>Новый туркменский манат</Name>" +
            "<Value>21,1655</Value>" +
            "</Valute>" +
            "<Valute ID=\"R01717\">" +
            "<NumCode>860</NumCode>" +
            "<CharCode>UZS</CharCode>" +
            "<Nominal>10000</Nominal>" +
            "<Name>Узбекских сумов</Name>" +
            "<Value>70,6312</Value>" +
            "</Valute>" +
            "<Valute ID=\"R01720\">" +
            "<NumCode>980</NumCode>" +
            "<CharCode>UAH</CharCode>" +
            "<Nominal>10</Nominal>" +
            "<Name>Украинских гривен</Name>" +
            "<Value>26,2690</Value>" +
            "</Valute>" +
            "<Valute ID=\"R01760\">" +
            "<NumCode>203</NumCode>" +
            "<CharCode>CZK</CharCode>" +
            "<Nominal>10</Nominal>" +
            "<Name>Чешских крон</Name>" +
            "<Value>34,0719</Value>" +
            "</Valute>" +
            "<Valute ID=\"R01770\">" +
            "<NumCode>752</NumCode>" +
            "<CharCode>SEK</CharCode>" +
            "<Nominal>10</Nominal>" +
            "<Name>Шведских крон</Name>" +
            "<Value>87,9925</Value>" +
            "</Valute>" +
            "<Valute ID=\"R01775\">" +
            "<NumCode>756</NumCode>" +
            "<CharCode>CHF</CharCode>" +
            "<Nominal>1</Nominal>" +
            "<Name>Швейцарский франк</Name>" +
            "<Value>82,9671</Value>" +
            "</Valute>" +
            "<Valute ID=\"R01810\">" +
            "<NumCode>710</NumCode>" +
            "<CharCode>ZAR</CharCode>" +
            "<Nominal>10</Nominal>" +
            "<Name>Южноафриканских рэндов</Name>" +
            "<Value>48,3061</Value>" +
            "</Valute>" +
            "<Valute ID=\"R01815\">" +
            "<NumCode>410</NumCode>" +
            "<CharCode>KRW</CharCode>" +
            "<Nominal>1000</Nominal>" +
            "<Name>Вон Республики Корея</Name>" +
            "<Value>66,8500</Value>" +
            "</Valute>" +
            "<Valute ID=\"R01820\">" +
            "<NumCode>392</NumCode>" +
            "<CharCode>JPY</CharCode>" +
            "<Nominal>100</Nominal>" +
            "<Name>Японских иен</Name>" +
            "<Value>71,2860</Value>" +
            "</Valute>" +
            "</ValCurs>";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency);

        //getXMLData();
        viewTableCurr(xmlData);// for debugging, after debug it call must be delete
    }

    void getXMLData(){
        //String url = "https://www.cbr.ru/scripts/XML_daily.asp";
        String url = "https://www.cbr.ru/scripts/XML_daily.asp?date_req=02/01/2001";
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest requestForApplications = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                viewTableCurr(response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("ERR_GET_REQ", error.getMessage());
                Log.d("ERR_GET_REQ_TAG", "StatusCode: " + error.networkResponse.statusCode);
            }
        });

        // Set the tag on the request.
        requestForApplications.setTag("Tag_for_get_xml_request");
        queue.add(requestForApplications);
        queue.start();
    }

    protected void viewTableCurr(String response) {
        TableLayout table = (TableLayout) findViewById(R.id.tableLayout);
        table.setColumnShrinkable(0, true);

        InputStream inStream = new ByteArrayInputStream(response.getBytes(StandardCharsets.UTF_8));

        try {
            // Create tree builder
            DocumentBuilder docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            // Create DOM tree from file
            Document document = docBuilder.parse(inStream);

            // Getting the root elem and nodes belong to concrete tag
            Element root = document.getDocumentElement();
            NodeList nodeList = root.getElementsByTagName("Valute");

            if(nodeList.getLength() > 0) {
                for(int i = 0; i < nodeList.getLength(); i++) {
                    Element elem = (Element) nodeList.item(i);
                    Element code = (Element) elem.getElementsByTagName("CharCode").item(0);
                    Element nominal = (Element) elem.getElementsByTagName("Nominal").item(0);
                    Element name = (Element) elem.getElementsByTagName("Name").item(0);
                    Element value = (Element) elem.getElementsByTagName("Value").item(0);

                    final String charCode = code.getFirstChild().getNodeValue();
                    final String nominalCurr = nominal.getFirstChild().getNodeValue();
                    final String nameCurr = name.getFirstChild().getNodeValue();
                    final String valueCurr = value.getFirstChild().getNodeValue();

                    TableRow row = new TableRow(this);
                    row.setLayoutParams(new TableRow.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,
                            TableLayout.LayoutParams.WRAP_CONTENT));
                    TextView column1 = new TextView(this);
                    TextView column2 = new TextView(this);
                    column1.setTextSize(17);
                    column2.setTextSize(17);
                    column1.setPadding(40, 0, 0,0);
                    column2.setPadding(130, 0, 0,0);

                    column1.setText(nameCurr);
                    column1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            saveAndGo(charCode, nominalCurr,valueCurr);
                        }
                    });
                    row.addView(column1);

                    column2.setText(charCode);
                    column2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            saveAndGo(charCode, nominalCurr,valueCurr);
                        }
                    });
                    row.addView(column2);

                    table.addView(row);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void saveAndGo(String charCode, String nominalCurr, String valueCurr){
        Intent intent = getIntent();
        int btnClick = intent.getIntExtra("btn_click", 0);
        String value = intent.getStringExtra("value_for_convert");

        if(btnClick == 1){
            String currTo = intent.getStringExtra("curr_to");
            String nominalTo = intent.getStringExtra("curr_nominal_to");
            String valueTo = intent.getStringExtra("value_to");

            intent = new Intent(getBaseContext(), MainActivity.class);

            intent.putExtra("curr_to", currTo);
            intent.putExtra("curr_nominal_to", nominalTo);
            intent.putExtra("value_to", valueTo);

            intent.putExtra("curr_from", charCode);
            intent.putExtra("curr_nominal_from", nominalCurr);
            intent.putExtra("value_from", valueCurr);
        } else {
            String currFrom = intent.getStringExtra("curr_from");
            String nominalFrom = intent.getStringExtra("curr_nominal_from");
            String valueFrom = intent.getStringExtra("value_from");

            intent = new Intent(getBaseContext(), MainActivity.class);

            intent.putExtra("curr_from", currFrom);
            intent.putExtra("curr_nominal_from", nominalFrom);
            intent.putExtra("value_from", valueFrom);

            intent.putExtra("curr_to", charCode);
            intent.putExtra("curr_nominal_to", nominalCurr);
            intent.putExtra("value_to", valueCurr);
        }

        intent.putExtra("changing", "true");
        intent.putExtra("value_for_convert", value);
        startActivity(intent);
    }
}
