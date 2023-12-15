import{r as d,y as S,U as I,N as _,z as $,R as g,B as T,aa as b,P as k}from"./index-ad304ebb.js";const w=d.createContext({padding:0}),L=w.Provider,V=()=>d.useContext(w).padding;var A=S((r,{padding:e,withBorder:a,inheritPadding:t})=>{const o=I({size:e,sizes:r.spacing}),n=`calc(-1 * ${o})`,s=r.colorScheme==="dark"?r.colors.dark[4]:r.colors.gray[3];return{cardSection:{display:"block",marginLeft:n,marginRight:n,paddingLeft:t?o:void 0,paddingRight:t?o:void 0,borderTop:a?`${_(1)} solid ${s}`:void 0,borderBottom:a?`${_(1)} solid ${s}`:void 0,"& + &":{borderTop:0},"&[data-first]":{marginTop:n,borderTop:0,borderBottom:a?`${_(1)} solid ${s}`:void 0},"&[data-last]":{marginBottom:n,borderBottom:0}}}});const D=A;var U=Object.defineProperty,c=Object.getOwnPropertySymbols,h=Object.prototype.hasOwnProperty,N=Object.prototype.propertyIsEnumerable,C=(r,e,a)=>e in r?U(r,e,{enumerable:!0,configurable:!0,writable:!0,value:a}):r[e]=a,q=(r,e)=>{for(var a in e||(e={}))h.call(e,a)&&C(r,a,e[a]);if(c)for(var a of c(e))N.call(e,a)&&C(r,a,e[a]);return r},F=(r,e)=>{var a={};for(var t in r)h.call(r,t)&&e.indexOf(t)<0&&(a[t]=r[t]);if(r!=null&&c)for(var t of c(r))e.indexOf(t)<0&&N.call(r,t)&&(a[t]=r[t]);return a};const G={withBorder:!1,inheritPadding:!1},x=d.forwardRef((r,e)=>{const a=$("CardSection",G,r),{className:t,withBorder:o,inheritPadding:n,unstyled:s,variant:p}=a,l=F(a,["className","withBorder","inheritPadding","unstyled","variant"]),{classes:m,cx:v}=D({padding:V(),withBorder:o,inheritPadding:n},{name:"Card",unstyled:s,variant:p});return g.createElement(T,q({className:v(m.cardSection,t),ref:e},l))});x.displayName="@mantine/core/CardSection";const E=b(x);var H=S(r=>({root:{position:"relative",overflow:"hidden",backgroundColor:r.colorScheme==="dark"?r.colors.dark[6]:r.white}}));const J=H;var K=Object.defineProperty,f=Object.getOwnPropertySymbols,R=Object.prototype.hasOwnProperty,j=Object.prototype.propertyIsEnumerable,O=(r,e,a)=>e in r?K(r,e,{enumerable:!0,configurable:!0,writable:!0,value:a}):r[e]=a,M=(r,e)=>{for(var a in e||(e={}))R.call(e,a)&&O(r,a,e[a]);if(f)for(var a of f(e))j.call(e,a)&&O(r,a,e[a]);return r},Q=(r,e)=>{var a={};for(var t in r)R.call(r,t)&&e.indexOf(t)<0&&(a[t]=r[t]);if(r!=null&&f)for(var t of f(r))e.indexOf(t)<0&&j.call(r,t)&&(a[t]=r[t]);return a};const W={padding:"md"},y=d.forwardRef((r,e)=>{const a=$("Card",W,r),{className:t,padding:o,radius:n,children:s,unstyled:p,variant:l}=a,m=Q(a,["className","padding","radius","children","unstyled","variant"]),{classes:v,cx:B}=J(null,{name:"Card",unstyled:p,variant:l}),u=d.Children.toArray(s),z=u.map((i,P)=>typeof i=="object"&&i&&"type"in i&&i.type===E?d.cloneElement(i,{variant:l,padding:o,"data-first":P===0||void 0,"data-last":P===u.length-1||void 0}):i);return g.createElement(L,{value:{padding:o}},g.createElement(k,M({className:B(v.root,t),radius:n,p:o,ref:e},m),z))});y.Section=E;y.displayName="@mantine/core/Card";const Y=b(y);export{Y as C};
