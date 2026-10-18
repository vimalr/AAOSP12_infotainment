package in.horsewaves.automotive;

import android.app.Activity;
import android.os.Bundle;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

/** Native, interactive AAOSP 12 automotive UI prototype. Vehicle values are simulated. */
public class MainActivity extends Activity {
    private final int bg = Color.rgb(5, 10, 18), panel = Color.rgb(13, 25, 41), blue = Color.rgb(0, 111, 255);
    private final int white = Color.rgb(240, 246, 255), muted = Color.rgb(160, 181, 204), green = Color.rgb(0, 230, 143);
    private LinearLayout root, body;
    private String current = "Home";
    private final String[] pages = {"Home", "Navigation", "Media", "Apps", "Phone", "Vehicle", "Climate", "EV Cluster", "Settings"};

    @Override public void onCreate(Bundle state) { super.onCreate(state); getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY); showPage("Home"); }

    private GradientDrawable shape(int color, int stroke) {
        GradientDrawable d = new GradientDrawable(); d.setColor(color); d.setCornerRadius(dp(16));
        if (stroke != 0) d.setStroke(dp(1), stroke); return d;
    }
    private int dp(float v) { return (int)(v * getResources().getDisplayMetrics().density + .5f); }
    private TextView text(String s, int size, int color, boolean bold) {
        TextView t = new TextView(this); t.setText(s); t.setTextSize(size); t.setTextColor(color);
        if (bold) t.setTypeface(Typeface.DEFAULT, Typeface.BOLD); t.setGravity(Gravity.CENTER_VERTICAL); return t;
    }
    private LinearLayout row() { LinearLayout l = new LinearLayout(this); l.setOrientation(LinearLayout.HORIZONTAL); l.setGravity(Gravity.CENTER_VERTICAL); return l; }
    private LinearLayout col() { LinearLayout l = new LinearLayout(this); l.setOrientation(LinearLayout.VERTICAL); return l; }
    private LinearLayout.LayoutParams lp(int w, int h) { return new LinearLayout.LayoutParams(w < 0 ? w : dp(w), h < 0 ? h : dp(h)); }
    private void gap(LinearLayout l, int h) { View v = new View(this); l.addView(v, lp(1,h)); }
    private Button button(String label, Runnable action) {
        Button b = new Button(this); b.setText(label); b.setTextColor(white); b.setAllCaps(false); b.setTextSize(14); b.setPadding(dp(8),dp(6),dp(8),dp(6));
        b.setBackground(shape(panel, Color.rgb(35,75,115))); b.setOnClickListener(v -> action.run()); return b;
    }
    private void addButton(LinearLayout parent, String label, Runnable action, int width) {
        Button b = button(label, action); LinearLayout.LayoutParams p = lp(width, 64); p.setMargins(dp(4),dp(4),dp(4),dp(4)); parent.addView(b,p);
    }
    private void card(String title, String value, String detail, int accent) {
        LinearLayout c = col(); c.setPadding(dp(14),dp(10),dp(14),dp(10)); c.setBackground(shape(panel, Color.rgb(29,55,82)));
        c.addView(text(title,13,muted,true)); c.addView(text(value,25,accent,true)); c.addView(text(detail,12,muted,false));
        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(0, dp(100), 1); p.setMargins(dp(5),dp(5),dp(5),dp(5)); body.addView(c,p);
    }
    private void section(String title, String subtitle) {
        body.addView(text(title,25,white,true)); if (subtitle != null) body.addView(text(subtitle,14,muted,false)); gap(body,12);
    }
    private void showPage(String page) {
        current = page; root = col(); root.setBackgroundColor(bg); setContentView(root);
        LinearLayout top = row(); top.setPadding(dp(14),dp(6),dp(14),dp(6)); top.setBackgroundColor(Color.rgb(8,17,29));
        top.addView(text("◈  HORSEWAVES",17,blue,true), new LinearLayout.LayoutParams(0,dp(42),1));
        top.addView(text("10:24 AM   ◉  Wi-Fi  ▰",13,muted,true)); root.addView(top,lp(-1,48));
        LinearLayout content = row(); root.addView(content,new LinearLayout.LayoutParams(-1,0,1));
        LinearLayout nav = col(); nav.setPadding(dp(5),dp(6),dp(5),dp(6)); nav.setBackgroundColor(Color.rgb(7,15,25)); content.addView(nav,lp(68,-1));
        String[] icons={"⌂","➤","♫","☎","▣","❄","◉","⚙"}; String[] destinations={"Home","Navigation","Media","Phone","Vehicle","Climate","EV Cluster","Settings"};
        for(int i=0;i<icons.length;i++){ final String dest=destinations[i]; Button b=button(icons[i],()->showPage(dest)); b.setTextSize(22); b.setBackground(shape(page.equals(dest)?blue:Color.rgb(7,15,25),0)); nav.addView(b,lp(-1,48)); }
        ScrollView scroll = new ScrollView(this); content.addView(scroll,new LinearLayout.LayoutParams(0,-1,1)); body=col(); body.setPadding(dp(18),dp(16),dp(18),dp(18)); scroll.addView(body);
        switch(page) {
            case "Home": home(); break;
            case "Navigation": navigation(); break;
            case "Media": media(); break;
            case "Apps": apps(); break;
            case "Phone": phone(); break;
            case "Vehicle": vehicle(); break;
            case "Climate": climate(); break;
            case "EV Cluster": cluster(); break;
            default: settings(); break;
        }
        LinearLayout footer=row(); footer.setPadding(dp(10),dp(5),dp(10),dp(5)); footer.setBackgroundColor(Color.rgb(8,17,29)); footer.addView(text("AAOSP 12 • UI prototype • Vehicle values are simulated",12,muted,false)); root.addView(footer,lp(-1,30));
    }
    private void home() {
        section("Good evening", "Automotive infotainment • Prototype");
        LinearLayout r=row(); body.addView(r,lp(-1,130));
        String[] names={"Navigation","Media","Phone"}; String[] desc={"Find your way","Play your music","Connect device"}; int[] colors={Color.rgb(0,83,195),Color.rgb(91,25,174),Color.rgb(0,130,91)};
        for(int i=0;i<3;i++){final String n=names[i]; LinearLayout c=col(); c.setPadding(dp(14),dp(12),dp(10),dp(8)); c.setBackground(shape(colors[i],0)); c.addView(text(names[i],20,white,true)); c.addView(text(desc[i],13,white,false)); c.setOnClickListener(v->showPage(n)); LinearLayout.LayoutParams p=new LinearLayout.LayoutParams(0,-1,1);p.setMargins(dp(5),0,dp(5),0);r.addView(c,p);}
        gap(body,14); LinearLayout r2=row(); body.addView(r2,lp(-1,100)); String[] labels={"Vehicle","Climate","Apps","EV Cluster","Settings"};
        for(String n:labels){Button b=button("▣\n"+n,()->showPage(n));r2.addView(b,new LinearLayout.LayoutParams(0,-1,1));}
        gap(body,12); section("Quick vehicle overview", "Illustrative data only"); LinearLayout metrics=row();body.addView(metrics,lp(-1,100)); card("SPEED","56 km/h","Current speed (demo)",green);card("BATTERY","80%","State of charge (demo)",green);card("RANGE","420 km","Estimated range (demo)",blue);
    }
    private void navigation(){section("Navigation", "Route preview • Map provider integration not configured"); LinearLayout map=col();map.setGravity(Gravity.CENTER);map.setBackground(shape(Color.rgb(12,35,62),blue));map.addView(text("➤",50,blue,true));map.addView(text("AVINASHI ROAD",18,white,true));map.addView(text("300 m  •  Turn right",16,green,true));map.addView(text("Coimbatore • Demo route only",13,muted,false));body.addView(map,lp(-1,260));gap(body,10);LinearLayout r=row();body.addView(r);addButton(r,"Start route",()->toast("Connect a navigation provider to enable routing"),150);addButton(r,"Route options",()->toast("Route options are prototype controls"),150);}
    private void media(){section("Media Player", "Choose a source or use the playback controls");body.addView(text("♫",55,blue,true));body.addView(text("Driving Forward",26,white,true));body.addView(text("Horsewaves Audio • Automotive Playlist",15,muted,false));gap(body,16);LinearLayout r=row();body.addView(r,lp(-1,70));addButton(r,"⏮ Previous",()->toast("Previous track (demo)"),140);addButton(r,"▶  Play / Pause",()->toast("Playback needs a media service"),160);addButton(r,"⏭ Next",()->toast("Next track (demo)"),140);LinearLayout s=row();body.addView(s,lp(-1,60));for(String x:new String[]{"USB","Bluetooth","Radio","Online"})addButton(s,x,()->toast(x+" source selected (demo)"),100);}
    private void apps(){section("Applications", "Automotive app launcher");LinearLayout r=null;String[] a={"Maps","Media","Phone","Vehicle Info","Climate","EV Cluster","Calendar","Settings","System Info"};for(int i=0;i<a.length;i++){if(i%3==0){r=row();body.addView(r,lp(-1,95));}final String n=a[i];addButton(r,n,()->showPage(n.equals("Vehicle Info")?"Vehicle":n.equals("System Info")?"Settings":n.equals("Maps")?"Navigation":n.equals("Media")?"Media":n.equals("Phone")?"Phone":n.equals("Climate")?"Climate":n.equals("EV Cluster")?"EV Cluster":n.equals("Settings")?"Settings":"Apps"),150);}}
    private void phone(){section("Phone", "Dialer mockup • no calls are placed");body.addView(text("Enter a number",18,muted,false));final TextView number=text("",28,white,true);body.addView(number);String[][] keys={{"1","2","3"},{"4","5","6"},{"7","8","9"},{"*","0","#"}};for(String[] line:keys){LinearLayout r=row();body.addView(r,lp(-1,58));for(String k:line)addButton(r,k,()->number.setText(number.getText().toString()+k),100);}LinearLayout r=row();body.addView(r,lp(-1,60));addButton(r,"⌫ Delete",()->{String s=number.getText().toString();if(!s.isEmpty())number.setText(s.substring(0,s.length()-1));},140);addButton(r,"☎ Call (demo)",()->toast("Phone connection is not configured"),160);}
    private void vehicle(){section("Vehicle Information", "Simulated values — connect VHAL for real signals");LinearLayout r=row();body.addView(r,lp(-1,105));card("SPEED","56 km/h","Vehicle speed",green);card("RPM","2200 rpm","Engine speed",blue);card("FUEL","65%","Fuel level",Color.rgb(255,178,0));LinearLayout r2=row();body.addView(r2,lp(-1,105));card("RANGE","420 km","Estimated range",blue);card("BATTERY","80%","State of charge",green);card("COOLANT","90 °C","Coolant temperature",Color.RED);body.addView(text("Production integration: read supported VehiclePropertyIds through the AAOS Car API / VHAL. Do not use demo values for driving decisions.",14,muted,false));}
    private void climate(){section("Climate Control", "UI demonstration • vehicle HVAC integration not configured");body.addView(text("Cabin temperature",17,muted,true));final TextView temp=text("22.0 °C",42,blue,true);temp.setGravity(Gravity.CENTER);body.addView(temp,lp(-1,80));LinearLayout r=row();body.addView(r,lp(-1,65));addButton(r,"−",()->temp.setText((Float.parseFloat(temp.getText().toString().replace(" °C",""))-0.5f)+" °C"),90);addButton(r,"+",()->temp.setText((Float.parseFloat(temp.getText().toString().replace(" °C",""))+0.5f)+" °C"),90);LinearLayout r2=row();body.addView(r2,lp(-1,65));for(String n:new String[]{"A/C","AUTO","SYNC","FRONT DEFROST","REAR DEFROST"})addButton(r2,n,()->toast(n+" selected (demo only)"),125);}
    private void cluster(){section("EV Smart Cluster", "Instrument cluster concept • simulated signals");LinearLayout r=row();body.addView(r,lp(-1,180));LinearLayout speed=col();speed.setGravity(Gravity.CENTER);speed.setBackground(shape(Color.rgb(5,22,39),blue));speed.addView(text("SPEED",14,muted,true));speed.addView(text("80",55,white,true));speed.addView(text("km/h",17,muted,false));r.addView(speed,new LinearLayout.LayoutParams(0,-1,1));LinearLayout power=col();power.setGravity(Gravity.CENTER);power.setBackground(shape(Color.rgb(5,22,39),green));power.addView(text("GEAR",14,muted,true));power.addView(text("D",48,white,true));power.addView(text("ECO  •  READY",15,green,true));LinearLayout.LayoutParams p=new LinearLayout.LayoutParams(0,-1,1);p.setMargins(dp(10),0,0,0);r.addView(power,p);LinearLayout r2=row();body.addView(r2,lp(-1,100));card("BATTERY","80%","State of charge",green);card("RANGE","420 km","Estimated range",blue);card("OUTSIDE","32 °C","Temperature",muted);}
    private void settings(){section("Settings", "Prototype controls and application information");for(String s:new String[]{"Network & Internet","Bluetooth","Display","Sound","Vehicle","Apps","System"}){LinearLayout r=row();r.setPadding(dp(12),0,dp(12),0);r.setBackground(shape(panel,Color.rgb(29,55,82)));r.addView(text(s,17,white,true),new LinearLayout.LayoutParams(0,dp(55),1));r.addView(button("Open",()->toast(s+" settings are managed by Android system")));LinearLayout.LayoutParams p=lp(-1,58);p.setMargins(0,dp(3),0,dp(3));body.addView(r,p);}gap(body,10);body.addView(text("App version 1.0.0\nTarget platform: AAOSP 12 / Android 12\nVehicle data: simulation only",14,muted,false));}
    private void toast(String s){Toast.makeText(this,s,Toast.LENGTH_SHORT).show();}
}
