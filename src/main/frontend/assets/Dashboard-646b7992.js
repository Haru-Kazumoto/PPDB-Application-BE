import{y as f,a$ as he,J as Z,K as L,r as A,N as ee,R as d,b0 as re,B as y,d as m,a as be,b1 as F,j as s,q as ye,a9 as _e,S as Pe,P as ae,G as k,b2 as we,ar as te}from"./index-af637abb.js";const V={xs:f(3),sm:f(5),md:f(8),lg:f(12),xl:f(16)},xe=he({from:{backgroundPosition:"0 0"},to:{backgroundPosition:`${f(40)} 0`}});var Oe=Z((r,{color:e,radius:a},{size:t})=>({root:{position:"relative",height:L({size:t,sizes:V}),backgroundColor:r.colorScheme==="dark"?r.colors.dark[4]:r.colors.gray[2],borderRadius:r.fn.radius(a),overflow:"hidden"},bar:{position:"absolute",top:0,bottom:0,left:0,height:"100%",display:"flex",justifyContent:"center",alignItems:"center",backgroundColor:r.fn.variant({variant:"filled",primaryFallback:!1,color:e||r.primaryColor}).background,transition:"width 100ms linear","&[data-animate]":{animation:`${xe} 1000ms linear infinite`},"&[data-striped]":{backgroundSize:`${f(20)} ${f(20)}`,backgroundImage:"linear-gradient(45deg, rgba(255, 255, 255, 0.15) 25%, transparent 25%, transparent 50%, rgba(255, 255, 255, 0.15) 50%, rgba(255, 255, 255, 0.15) 75%, transparent 75%, transparent)"},"&:last-of-type":{borderTopRightRadius:r.fn.radius(a),borderBottomRightRadius:r.fn.radius(a)},"&:first-of-type":{borderTopLeftRadius:r.fn.radius(a),borderBottomLeftRadius:r.fn.radius(a)},"@media (prefers-reduced-motion)":{transitionDuration:r.respectReducedMotion?"0ms":void 0}},label:{color:r.white,fontSize:`calc(${L({size:t,sizes:V})} * 0.65)`,fontWeight:700,userSelect:"none",overflow:"hidden",whiteSpace:"nowrap"}}));const je=Oe;var $e=Object.defineProperty,ke=Object.defineProperties,Re=Object.getOwnPropertyDescriptors,R=Object.getOwnPropertySymbols,se=Object.prototype.hasOwnProperty,oe=Object.prototype.propertyIsEnumerable,G=(r,e,a)=>e in r?$e(r,e,{enumerable:!0,configurable:!0,writable:!0,value:a}):r[e]=a,B=(r,e)=>{for(var a in e||(e={}))se.call(e,a)&&G(r,a,e[a]);if(R)for(var a of R(e))oe.call(e,a)&&G(r,a,e[a]);return r},le=(r,e)=>ke(r,Re(e)),U=(r,e)=>{var a={};for(var t in r)se.call(r,t)&&e.indexOf(t)<0&&(a[t]=r[t]);if(r!=null&&R)for(var t of R(r))e.indexOf(t)<0&&oe.call(r,t)&&(a[t]=r[t]);return a};function Se(r){return r.reduce((e,a)=>(e.sections.push(le(B({},a),{accumulated:e.accumulated})),e.accumulated+=a.value,e),{accumulated:0,sections:[]}).sections}const Ne={size:"md",radius:"sm",striped:!1,animate:!1,label:""},ne=A.forwardRef((r,e)=>{const a=ee("Progress",Ne,r),{className:t,value:c,color:p,size:u,radius:l,striped:o,animate:i,label:n,"aria-label":g,classNames:v,styles:_,sections:x,unstyled:C,variant:O}=a,E=U(a,["className","value","color","size","radius","striped","animate","label","aria-label","classNames","styles","sections","unstyled","variant"]),{classes:h,cx:b,theme:j}=je({color:p,radius:l},{name:"Progress",classNames:v,styles:_,unstyled:C,variant:O,size:u}),D=Array.isArray(x)?Se(x).map((I,z)=>{var $=I,{tooltip:T,accumulated:me,value:ve,label:W,color:ge}=$,J=U($,["tooltip","accumulated","value","label","color"]);return d.createElement(re.Floating,{label:T,disabled:!T,key:z},d.createElement(y,le(B({},J),{className:b(h.bar,J.className),"data-striped":o||i||void 0,"data-animate":i||void 0,sx:{width:`${ve}%`,left:`${me}%`,backgroundColor:j.fn.variant({variant:"filled",primaryFallback:!1,color:ge||j.primaryColor}).background}}),W&&d.createElement(m,{className:h.label},W)))}):null;return d.createElement(y,B({className:b(h.root,t),ref:e},E),D||d.createElement("div",{role:"progressbar","aria-valuemax":100,"aria-valuemin":0,"aria-valuenow":c,"aria-label":g,className:h.bar,style:{width:`${c}%`},"data-striped":o||i||void 0,"data-animate":i||void 0},n?d.createElement(m,{className:h.label},n):""))});ne.displayName="@mantine/core/Progress";function Ce({size:r,thickness:e,sum:a,value:t,root:c,offset:p}){const u=(r*.9-e*2)/2,l=Math.PI*u*2/100,o=c?`${(100-a)*l}, ${a*l}`:`${t*l}, ${(100-t)*l}`;return{strokeWidth:e,cx:r/2,cy:r/2,r:u,transform:c?`scale(1, -1) translate(0, -${r})`:null,strokeDasharray:o,strokeDashoffset:c?0:p}}var Ee=Object.defineProperty,De=Object.defineProperties,Ie=Object.getOwnPropertyDescriptors,S=Object.getOwnPropertySymbols,ie=Object.prototype.hasOwnProperty,ce=Object.prototype.propertyIsEnumerable,q=(r,e,a)=>e in r?Ee(r,e,{enumerable:!0,configurable:!0,writable:!0,value:a}):r[e]=a,K=(r,e)=>{for(var a in e||(e={}))ie.call(e,a)&&q(r,a,e[a]);if(S)for(var a of S(e))ce.call(e,a)&&q(r,a,e[a]);return r},ze=(r,e)=>De(r,Ie(e)),Me=(r,e)=>{var a={};for(var t in r)ie.call(r,t)&&e.indexOf(t)<0&&(a[t]=r[t]);if(r!=null&&S)for(var t of S(r))e.indexOf(t)<0&&ce.call(r,t)&&(a[t]=r[t]);return a};function de(r){var e=r,{size:a,value:t,offset:c,sum:p,thickness:u,root:l,color:o,lineRoundCaps:i,tooltip:n}=e,g=Me(e,["size","value","offset","sum","thickness","root","color","lineRoundCaps","tooltip"]);const v=be(),_=v.fn.themeColor(o||(v.colorScheme==="dark"?"dark":"gray"),o?v.fn.primaryShade():v.colorScheme==="dark"?4:1,!1);return d.createElement(re.Floating,{disabled:!n,label:n},d.createElement("circle",K(ze(K({},g),{fill:"none",strokeLinecap:i?"round":"butt",stroke:_}),Ce({sum:p,size:a,thickness:u,value:t,offset:c,root:l}))))}de.displayName="@mantine/core/Curve";var Be=Object.defineProperty,Ae=Object.defineProperties,Fe=Object.getOwnPropertyDescriptors,Y=Object.getOwnPropertySymbols,Te=Object.prototype.hasOwnProperty,We=Object.prototype.propertyIsEnumerable,H=(r,e,a)=>e in r?Be(r,e,{enumerable:!0,configurable:!0,writable:!0,value:a}):r[e]=a,P=(r,e)=>{for(var a in e||(e={}))Te.call(e,a)&&H(r,a,e[a]);if(Y)for(var a of Y(e))We.call(e,a)&&H(r,a,e[a]);return r},w=(r,e)=>Ae(r,Fe(e));function Je({size:r,thickness:e,sections:a,renderRoundedLineCaps:t,rootColor:c}){const p=a.reduce((n,g)=>n+g.value,0),u=Math.PI*((r*.9-e*2)/2)*2;let l=u;const o=[],i=[];for(let n=0;n<a.length;n+=1)o.push({sum:p,offset:l,data:a[n],root:!1}),l-=a[n].value/100*u;if(o.push({sum:p,offset:l,data:{color:c},root:!0}),i.push(w(P({},o[o.length-1]),{lineRoundCaps:!1})),o.length>2){i.push(w(P({},o[0]),{lineRoundCaps:t})),i.push(w(P({},o[o.length-2]),{lineRoundCaps:t}));for(let n=1;n<=o.length-3;n+=1)i.push(w(P({},o[n]),{lineRoundCaps:!1}))}else i.push(w(P({},o[0]),{lineRoundCaps:t}));return i}var Le=Z({root:{position:"relative"},label:{position:"absolute",top:"50%",transform:"translateY(-50%)"}});const Ve=Le;var Ge=Object.defineProperty,Ue=Object.defineProperties,qe=Object.getOwnPropertyDescriptors,N=Object.getOwnPropertySymbols,pe=Object.prototype.hasOwnProperty,ue=Object.prototype.propertyIsEnumerable,Q=(r,e,a)=>e in r?Ge(r,e,{enumerable:!0,configurable:!0,writable:!0,value:a}):r[e]=a,M=(r,e)=>{for(var a in e||(e={}))pe.call(e,a)&&Q(r,a,e[a]);if(N)for(var a of N(e))ue.call(e,a)&&Q(r,a,e[a]);return r},Ke=(r,e)=>Ue(r,qe(e)),Ye=(r,e)=>{var a={};for(var t in r)pe.call(r,t)&&e.indexOf(t)<0&&(a[t]=r[t]);if(r!=null&&N)for(var t of N(r))e.indexOf(t)<0&&ue.call(r,t)&&(a[t]=r[t]);return a};const He={size:120,thickness:12},fe=A.forwardRef((r,e)=>{const a=ee("RingProgress",He,r),{className:t,style:c,label:p,sections:u,size:l,thickness:o,classNames:i,styles:n,roundCaps:g,rootColor:v,unstyled:_,variant:x}=a,C=Ye(a,["className","style","label","sections","size","thickness","classNames","styles","roundCaps","rootColor","unstyled","variant"]),{classes:O,cx:E}=Ve(null,{name:"RingProgress",classNames:i,styles:n,unstyled:_,variant:x}),h=Je({size:l,thickness:o,sections:u,renderRoundedLineCaps:g,rootColor:v}).map(({data:b,sum:j,root:D,lineRoundCaps:I,offset:z},$)=>d.createElement(de,Ke(M({},b),{key:$,size:l,thickness:o,sum:j,offset:z,color:b==null?void 0:b.color,root:D,lineRoundCaps:I})));return d.createElement(y,M({style:M({width:l,height:l},c),className:E(O.root,t),ref:e},C),d.createElement("svg",{style:{width:l,height:l,transform:"rotate(-90deg)"}},h),p&&d.createElement("div",{className:O.label,style:{right:o*2,left:o*2}},p))});fe.displayName="@mantine/core/RingProgress";var Qe=F("arrow-down-right","IconArrowDownRight",[["path",{d:"M7 7l10 10",key:"svg-0"}],["path",{d:"M17 8l0 9l-9 0",key:"svg-1"}]]),Xe=F("arrow-up-right","IconArrowUpRight",[["path",{d:"M17 7l-10 10",key:"svg-0"}],["path",{d:"M8 7l9 0l0 9",key:"svg-1"}]]),Ze=F("device-analytics","IconDeviceAnalytics",[["path",{d:"M3 4m0 1a1 1 0 0 1 1 -1h16a1 1 0 0 1 1 1v10a1 1 0 0 1 -1 1h-16a1 1 0 0 1 -1 -1z",key:"svg-0"}],["path",{d:"M7 20l10 0",key:"svg-1"}],["path",{d:"M9 16l0 4",key:"svg-2"}],["path",{d:"M15 16l0 4",key:"svg-3"}],["path",{d:"M8 12l3 -3l2 2l3 -3",key:"svg-4"}]]);const X=[{label:"Jalur Reguler",count:"204,001",part:59,color:"#47d6ab"},{label:"Jalur Prestasi",count:"121,017",part:35,color:"#03141a"},{label:"Jalur Diskon",count:"31,118",part:6,color:"#4fcdf7"}],er={up:Xe,down:Qe},rr=[{label:"Siswa Terdaftar",stats:"490.203",progress:90,color:"red",icon:"down"},{label:"Menunggu Pembayaran",stats:"456,578",progress:65,color:"teal",icon:"up"},{label:"Pembayaran Terkonfirmasi",stats:"2,550",progress:40,color:"blue",icon:"up"}],ar=()=>{const r=rr.map(e=>{const a=er[e.icon];return s.jsx(ae,{withBorder:!0,radius:"md",p:"xs",children:s.jsxs(k,{children:[s.jsx(fe,{size:80,roundCaps:!0,thickness:8,sections:[{value:e.progress,color:e.color}],label:s.jsx(we,{children:s.jsx(a,{style:{width:f(20),height:f(20)},stroke:1.5})})}),s.jsxs("div",{children:[s.jsx(m,{c:"dimmed",size:"xs",tt:"uppercase",fw:700,children:e.label}),s.jsx(m,{fw:700,size:"xl",children:e.stats})]})]})},e.label)});return s.jsx(te,{breakpoints:[{maxWidth:"xs",cols:1},{minWidth:"md",cols:3}],children:r})},tr=()=>{const r=X.map(e=>s.jsxs(y,{style:{borderColor:e.color,borderBottomWidth:.5,borderBottomStyle:"solid",paddingBottom:5},children:[s.jsx(m,{tt:"uppercase",fz:"xs",c:"dimmed",fw:700,children:e.label}),s.jsxs(k,{justify:"space-between",align:"flex-end",gap:0,children:[s.jsx(m,{fw:700,children:e.count}),s.jsxs(m,{c:e.color,fw:700,size:"sm",children:[e.part,"%"]})]})]},e.label));return s.jsxs(ae,{withBorder:!0,p:"md",radius:"md",children:[s.jsx(m,{size:"lg",weight:500,mb:10,children:"Statistik Berdasarkan Jalur Pendaftaran"}),s.jsxs(k,{justify:"space-between",children:[s.jsx(k,{align:"flex-end",gap:"xs",children:s.jsx(m,{fz:"xl",fw:700,children:"345,765"})}),s.jsx(Ze,{size:"1.4rem",stroke:1.5})]}),s.jsx(ne,{size:"xl",sections:X.map(e=>({value:e.part,color:e.color}))}),s.jsx(te,{breakpoints:[{maxWidth:"xs",cols:1},{minWidth:"md",cols:3}],mt:"xl",children:r})]})},or=()=>(A.useMemo(()=>[{id:"No. Formulir",header:"No. Formulir",accessorFn:(r,e)=>e+1},{id:"Nama",header:"Nama",accessorFn:(r,e)=>e+1}],[]),s.jsx(s.Fragment,{children:s.jsxs(ye,{title:"Dashboard",children:[s.jsx(_e,{label:"Dashboard"}),s.jsxs(Pe,{className:"max-w-[70rem] mx-auto",children:[s.jsx(y,{mb:"lg",mt:40,children:s.jsx(ar,{})}),s.jsx(y,{mb:"lg",children:s.jsx(tr,{})})]})]})}));export{or as default};
