import{u as w,a as v,b as K,j as e,B as n,T as y,D as $,c,L as m,d as G,e as L,r as j,f as P,H as Y,G as k,g as E,P as q,S as H,A as D,F as M,h as J,i as U}from"./index-b070c93a.js";import{G as O}from"./getAlur-723740d8.js";import{T}from"./tiptapOutput-5b46d7f7.js";import{S}from"./Stepper-b1a3574f.js";import{C as h}from"./Card-33bd5c15.js";import{I as z}from"./Image-5f23b392.js";import"./createReactComponent-4a606c3a.js";import"./Tooltip-61f8e4ea.js";import"./SimpleGrid-737d8ee7.js";const Q=()=>{var o,x;const{xs:a}=w(),r=v(),t=r.colorScheme==="dark",{data:s}=K({queryKey:["get_all_alur_pendaftaran"],queryFn:O}),i=(o=s==null?void 0:s.data)==null?void 0:o.filter(d=>d.grade==="SMP").sort((d,g)=>d.id-g.id),l=(x=s==null?void 0:s.data)==null?void 0:x.filter(d=>d.grade==="SMK").sort((d,g)=>d.id-g.id);return e.jsx(e.Fragment,{children:e.jsxs(n,{id:"alur-pendaftaran",mt:100,className:"flex flex-col",children:[e.jsx(n,{className:"bg-white shadow-md max rounded-full px-[4rem] flex justify-center mx-auto py-2",children:e.jsx(y,{size:a?"2.5vw":"5vw",color:"dark",weight:"bold",children:"Alur Pendaftaran SMP"})}),s&&(i==null?void 0:i.length)>0?e.jsx(n,{className:"mt-10 flex max-w-[60rem] w-full mx-auto",children:e.jsx(S,{active:0,orientation:"vertical",sx:{display:"flex",width:"100%",justifyContent:"space-between",flex:"1"},styles:{stepWrapper:{width:"3.5rem",height:"3.5rem"},stepIcon:{border:"none",background:"linear-gradient(to bottom, #2A166F, #420BFF)",color:"white",borderRadius:"100%",display:"flex",justifyContent:"center",alignItems:"center",width:"100%",height:"100%",boxShadow:`5px 5px 10px -5px ${t?"#291872":"black"}`},verticalSeparator:{borderLeft:`2px solid ${t?"#291872":"#020731"}`,position:"absolute",top:"calc(3.5rem + calc(2rem / 2))",left:"calc(3.5rem / 2)"},steps:{flex:1},step:{display:"flex",width:"100%",gap:a?"2rem":""},stepBody:{flex:1},stepDescription:{margin:0}},children:i&&(i==null?void 0:i.map(d=>e.jsx(S.Step,{mb:10,description:e.jsxs(n,{style:{backgroundColor:`${t?r.colors.dark[9]:"#dbe1fe"}`,color:`${t?"white":"black"}`,borderRadius:"12px",padding:`${a?"1rem":"0.5rem"}`},children:[e.jsxs("div",{style:{width:"fit-content",fontWeight:"bold",color:`${t?"#6449da":"#020731"}`},children:[e.jsx("h1",{style:{fontSize:"22px"},children:d.title}),e.jsx($,{size:4,color:t?"#6449da":"#020731",w:"60%"})]}),e.jsx("div",{style:{marginTop:"24px",display:"flex",flexDirection:"column",gap:"25px",lineHeight:"20px"},children:e.jsx(T,{desc:d.content})})]})},d.id)))})}):e.jsxs(n,{className:"mt-5 bg-white rounded-xl p-10 max-w-[25rem] mx-auto",children:[e.jsx(c,{align:"center",color:"dark",weight:"bold",children:"Alur Pendaftaran Kosong"}),e.jsxs(c,{align:"center",size:"sm",color:"dark",children:["Silakan hubungi ",e.jsx(c,{component:m,underline:!0,color:"blue",to:"https://wa.me/6281380908008",target:"_blank",children:"Admin"})," untuk info alur pendaftaran"]})]}),e.jsx(n,{className:"bg-white shadow-md max rounded-full px-[4rem] flex justify-center mx-auto py-2 mt-20",children:e.jsx(y,{size:a?"2.5vw":"5vw",color:"dark",weight:"bold",children:"Alur Pendaftaran SMK"})}),s&&(l==null?void 0:l.length)>0?e.jsx(n,{className:"mt-10 flex max-w-[60rem] w-full mx-auto",children:e.jsx(S,{active:0,orientation:"vertical",sx:{display:"flex",width:"100%",justifyContent:"space-between",flex:"1"},styles:{stepWrapper:{width:"3.5rem",height:"3.5rem"},stepIcon:{border:"none",background:"linear-gradient(to bottom, #2A166F, #420BFF)",color:"white",borderRadius:"100%",display:"flex",justifyContent:"center",alignItems:"center",width:"100%",height:"100%",boxShadow:`5px 5px 10px -5px ${t?"#291872":"black"}`},verticalSeparator:{borderLeft:`2px solid ${t?"#291872":"#020731"}`,position:"absolute",top:"calc(3.5rem + calc(2rem / 2))",left:"calc(3.5rem / 2)"},steps:{flex:1},step:{display:"flex",width:"100%",gap:a?"2rem":""},stepBody:{flex:1},stepDescription:{margin:0}},children:l&&(l==null?void 0:l.map(d=>e.jsx(S.Step,{mb:10,description:e.jsxs(n,{style:{backgroundColor:`${t?r.colors.dark[9]:"#dbe1fe"}`,color:`${t?"white":"black"}`,borderRadius:"12px",padding:`${a?"1rem":"0.5rem"}`},children:[e.jsxs("div",{style:{width:"fit-content",fontWeight:"bold",color:`${t?"#6449da":"#020731"}`},children:[e.jsx("h1",{style:{fontSize:"22px"},children:d.title}),e.jsx($,{size:4,color:t?"#6449da":"#020731",w:"60%"})]}),e.jsx("div",{style:{marginTop:"24px",display:"flex",flexDirection:"column",gap:"25px",lineHeight:"20px"},children:e.jsx(T,{desc:d.content})})]})},d.id)))})}):e.jsxs(n,{className:"mt-5 bg-white rounded-xl p-10 max-w-[25rem] mx-auto",children:[e.jsx(c,{align:"center",color:"dark",weight:"bold",children:"Alur Pendaftaran Kosong"}),e.jsxs(c,{align:"center",size:"sm",color:"dark",children:["Silakan hubungi ",e.jsx(c,{component:m,underline:!0,color:"blue",to:"https://wa.me/6281380908008",target:"_blank",children:"Admin"})," untuk info alur pendaftaran"]})]})]})})},X=async()=>(await G.get(L+"/public/index-registration-path")).data,W=({batch:a})=>{const{xs:r}=w(),t=v(),s=t.colorScheme==="dark";return e.jsxs(n,{id:"biaya",style:{backgroundColor:`${s?t.colors.dark[9]:"#dbe1fe"}`,color:`${s?"white":"#0F172A"}`,display:"flex",flexDirection:"column",marginTop:"20px",padding:`${r?"32px":"32px 8px"}`,borderRadius:"7px"},children:[e.jsxs("h1",{style:{fontSize:"22px"},children:["Biaya ",a==null?void 0:a.name,":"]}),a&&(a==null?void 0:a.additionalPrices.length)>0?a==null?void 0:a.additionalPrices.map(i=>e.jsxs(n,{style:{backgroundColor:`${s?"black":"white"}`,color:`${s?"white":"#0F172A"}`,display:"flex",flexDirection:"column",marginTop:"20px"},children:[e.jsx("div",{id:"judul-biaya",style:{borderBottom:"1px solid",padding:"8px 24px",textAlign:"center"},children:e.jsx("p",{children:i.namePrice})}),e.jsx("div",{children:i.priceDetails.map(l=>{const o=l.price.toLocaleString("id-ID",{style:"currency",currency:"IDR"});return e.jsxs("div",{style:{display:"flex",fontSize:"16px",padding:"8px 32px"},children:[e.jsx("span",{style:{flex:2},children:l.subTitle}),e.jsx("span",{style:{flex:1},children:o.endsWith(",00")?o.slice(0,-3):o})]},l.id)})})]},i.id)):"-"]})},R=({activeCard:a,setActiveCard:r,data:t,setBatch:s})=>{const i="bg-[#F36B1D] text-white border border border-black shadow",o=v().colorScheme==="dark";return e.jsx(n,{className:"overflow-hidden flex justify-center items-center",children:e.jsx(n,{id:"card-jalur-pendaftaran",className:"  text-gray-800 font-bold  overflow-x-hidden  flex max-w-[40rem] mx-auto",children:e.jsx(n,{className:"p-4 flex overflow-auto   flex-[1] gap-4",children:t==null?void 0:t.map(x=>e.jsx("button",{onClick:()=>{s(x),r(x.id)},className:`w-[200px] min-w-[200px] max-w-[200px] flex-grow min-h-[125px] p-[22px] font-black  transition-all ease-out rounded-lg  border
                             ${a===x.id?i:`${o?"bg-black text-white ":"bg-white"} text-black`}`,children:x.name},x.id))})})})},B=({batch:a})=>{var i,l;const{xs:r}=w(),t=v(),s=t.colorScheme==="dark";return console.log({batch:a}),e.jsxs(n,{id:"jadwal",className:`flex flex-col mt-5   rounded-md  ${r?"p-8 mt-10":"py-8 px-2"}`,style:{backgroundColor:`${s?t.colors.dark[9]:"#dbe1fe"}`,color:`${s?"white":"#0F172A"}`},children:[e.jsxs("h1",{className:"text-[22px] ",children:["Jadwal ",a==null?void 0:a.name,":"]}),a&&((i=a.registrationBatches)==null?void 0:i.length)>0?(l=a==null?void 0:a.registrationBatches)==null?void 0:l.map(o=>{var b;const x=new Date(o.start_date),d=new Date(o.end_date),g=x.toLocaleDateString("id-ID",{month:"long",day:"numeric"}),N=d.toLocaleDateString("id-ID",{year:"numeric",month:"long",day:"numeric"});return e.jsxs(n,{className:" text-[16px] mt-5",style:{backgroundColor:`${s?"black":"white"}`,color:`${s?"white":"#0F172A"}`},children:[e.jsx("div",{id:"judul-biaya",className:" border-b   py-2 text-[20px] text-center",children:e.jsx("span",{children:o.name})}),e.jsxs("div",{className:"font-semibold px-2",children:[e.jsxs("div",{className:"py-2 flex gap-4",children:[e.jsx("span",{className:"flex-1",children:"Pendaftaran Gelombang"}),e.jsxs("span",{className:"flex-1",children:[g," - ",N]})]}),(b=o==null?void 0:o.ujian_penerimaan)==null?void 0:b.map(u=>e.jsxs("div",{className:"py-2 flex gap-4",children:[e.jsx("span",{className:"flex-1",children:u.nama_ujian_penerimaan}),e.jsxs("span",{className:"flex-1",children:[u.waktu_dibuka," - ",u.waktu_ditutup]})]},u.id))]})]},o.id)}):"-"]})},Z=()=>{var u,A,C,F,_,I;const{xs:a}=w(),{data:r}=K({queryKey:["get_jalur_global"],queryFn:X}),t=(A=(u=r==null?void 0:r.data)==null?void 0:u.filter(p=>(p==null?void 0:p.grade)==="SMP"))==null?void 0:A.sort((p,f)=>p.id-f.id),s=(F=(C=r==null?void 0:r.data)==null?void 0:C.filter(p=>(p==null?void 0:p.grade)==="SMK"))==null?void 0:F.sort((p,f)=>p.id-f.id),[i,l]=j.useState((t==null?void 0:t.length)>0?(_=t[0])==null?void 0:_.id:null),[o,x]=j.useState((s==null?void 0:s.length)>0?(I=s[0])==null?void 0:I.id:null),[d,g]=j.useState(()=>(t==null?void 0:t.length)>0?t[0]:null),[N,b]=j.useState(()=>(s==null?void 0:s.length)>0?s[0]:null);return j.useEffect(()=>{var p,f;(t==null?void 0:t.length)>0&&(g(t[0]),l((p=t[0])==null?void 0:p.id)),(s==null?void 0:s.length)>0&&(b(s[0]),x((f=s[0])==null?void 0:f.id))},[r==null?void 0:r.data]),e.jsx(e.Fragment,{children:e.jsxs(n,{id:"jalur-pendaftaran",mt:100,sx:{display:"flex",gap:"20px",flexDirection:"column"},children:[e.jsx(n,{className:"bg-white shadow-md max rounded-full px-[4rem] flex justify-center mx-auto py-2",children:e.jsx(y,{size:a?"2.5vw":"5vw",color:"dark",weight:"bold",children:"Jalur Pendaftaran SMP"})}),(t==null?void 0:t.length)>0?e.jsxs(e.Fragment,{children:[e.jsx(R,{activeCard:i,setActiveCard:l,setBatch:g,data:t}),e.jsxs(n,{sx:{display:"flex",flexDirection:"column",width:"100%",fontSize:"22px",fontWeight:"bold",maxWidth:"50rem",marginInline:"auto"},children:[e.jsx(W,{batch:d}),e.jsx(B,{batch:d})]})]}):e.jsx(e.Fragment,{children:e.jsxs(n,{className:"mt-5 bg-white rounded-xl p-10 max-w-[25rem] mx-auto",children:[e.jsx(c,{align:"center",color:"dark",weight:"bold",children:"Jalur Pendaftaran Kosong"}),e.jsxs(c,{align:"center",size:"sm",color:"dark",children:["Silakan hubungi ",e.jsx(c,{component:m,underline:!0,color:"blue",to:"https://wa.me/6281380908008",target:"_blank",children:"Admin"})," untuk info jalur pendaftaran"]})]})}),e.jsx(n,{className:"bg-white shadow-md max rounded-full px-[4rem] flex justify-center mx-auto py-2 mt-20",children:e.jsx(y,{size:a?"2.5vw":"5vw",color:"dark",weight:"bold",children:"Jalur Pendaftaran SMK"})}),(t==null?void 0:t.length)>0?e.jsxs(e.Fragment,{children:[e.jsx(R,{activeCard:o,setActiveCard:x,setBatch:b,data:s}),e.jsxs(n,{sx:{display:"flex",flexDirection:"column",width:"100%",fontSize:"22px",fontWeight:"bold",maxWidth:"50rem",marginInline:"auto"},children:[e.jsx(W,{batch:N}),e.jsx(B,{batch:N})]})]}):e.jsx(e.Fragment,{children:e.jsxs(n,{className:"mt-5 bg-white rounded-xl p-10 max-w-[25rem] mx-auto",children:[e.jsx(c,{align:"center",color:"dark",weight:"bold",children:"Jalur Pendaftaran Kosong"}),e.jsxs(c,{align:"center",size:"sm",color:"dark",children:["Silakan hubungi ",e.jsx(c,{component:m,underline:!0,color:"blue",to:"https://wa.me/6281380908008",target:"_blank",children:"Admin"})," untuk info jalur pendaftaran"]})]})})]})})},V=()=>{const{xs:a}=w();return e.jsx(e.Fragment,{children:e.jsxs(n,{id:"ppdb",pt:60,className:"flex gap-10 lg:flex-row flex-col mx-auto",children:[e.jsxs(h,{shadow:"sm",padding:"xl",className:"group/card ",children:[e.jsx(h.Section,{inheritPadding:!0,withBorder:!0,py:"xs",children:e.jsx(c,{size:20,align:"center",weight:"bold",children:"Daftar PPDB SMK"})}),e.jsx(h.Section,{sx:{overflow:"hidden"},children:e.jsx(z,{className:" transition  duration-300 ease-in-out group-hover/card:scale-105 ",src:"/smk.jpg",height:a?260:200,width:a?500:300,alt:"smp"})}),e.jsx(h.Section,{p:20,children:e.jsx(P,{fullWidth:!0,color:"orange",component:m,to:"/ppdb/auth/register/smk",children:"Daftar"})})]}),e.jsxs(h,{shadow:"sm",padding:"xl",className:"group/card ",children:[e.jsx(h.Section,{inheritPadding:!0,withBorder:!0,py:"xs",children:e.jsx(c,{size:20,align:"center",weight:"bold",children:"Daftar PPDB SMP"})}),e.jsx(h.Section,{sx:{overflow:"hidden"},children:e.jsx(z,{className:" transition  duration-300 ease-in-out group-hover/card:scale-105 ",src:"/smp.jpg",height:a?260:200,width:a?500:300,alt:"smp"})}),e.jsx(h.Section,{p:20,children:e.jsx(P,{fullWidth:!0,className:"bg-[#2A166F]",component:m,to:"/ppdb/auth/register/smp",children:"Daftar"})})]})]})})},de=()=>{const{md:a,xs:r,lg:t}=w(),s=v(),i=s.colorScheme==="dark",l=j.useMemo(()=>[{label:"Beranda",path:"beranda"},{label:"Daftar",path:"ppdb"},{label:"Alur Pendaftaran",path:"alur-pendaftaran"},{label:"Jalur Pendaftaran",path:"jalur-pendaftaran"}],[]);return e.jsxs("main",{id:"dashboard-ppdb",children:[e.jsxs(Y,{height:"70px",sx:{boxShadow:`${i?"":"0px -40px 50px 10px black"}`,display:"flex",justifyContent:"space-between",alignItems:"center",paddingInline:`${a?"1.5rem":"1rem"}`,position:"fixed",backgroundColor:`${i?s.colors.dark[9]:"#2A166F"}`,border:"none"},children:[e.jsxs(k,{spacing:`${a?"md":"xs"}`,className:"max-lg:mx-auto",children:[e.jsx("img",{src:"/logo-yatindo-hd.png",alt:"Yatindo",className:"w-[47px]"}),e.jsx(n,{children:e.jsx(c,{weight:"bold",lineClamp:1,color:"white",children:"PPDB Yatindo"})})]}),e.jsx(e.Fragment,{children:a?e.jsx(e.Fragment,{children:e.jsx(k,{mr:20,children:l.map((o,x)=>e.jsx(E,{to:o.path,smooth:!0,duration:500,offset:-90,style:{cursor:"pointer",fontWeight:"bold",color:"white"},className:"hover:underline underline-offset-2",children:o.label},x))})}):""})]}),e.jsx(q,{className:"style-box bg-gray-100 parralax ",children:e.jsxs(H,{pt:"70px",px:r?40:20,py:90,className:" min-h-[80vh] backdrop-brightness-[0.3] backdrop-blur-sm   text-white ",children:[e.jsx(n,{className:"text-center mx-auto",py:140,id:"beranda",children:e.jsxs(k,{spacing:`${a?"40px":"xs"}`,children:[e.jsxs(n,{className:"text-left",children:[e.jsxs(k,{className:"flex",children:[e.jsx("img",{src:"/logo-yatindo-hd.png",alt:"Yatindo",className:"w-[57px]"}),e.jsx($,{orientation:"vertical",size:"xs",color:"white"}),e.jsx(c,{weight:"bold",lineClamp:1,color:"white",size:20,children:"PPDB Yatindo"})]}),e.jsx(y,{mt:10,size:40,children:"Yayasan Tinta Emas Indonesia"}),e.jsx(c,{className:"max-w-xl",mt:10,size:16,children:"Yayasan Tinta Emas Indonesia, Jl. Asem Jaya No.1, RT.004/RW.005, Mustika Jaya, Kec. Mustika Jaya, Kota Bks, Jawa Barat 17158"}),e.jsxs(k,{mt:20,children:[e.jsx(D,{variant:"filled",color:"indigo",size:45,component:m,to:"https://wa.me/6281380908008?text=Halo Admin Yatindo",target:"_blank",children:e.jsx(M,{size:30})}),e.jsx(D,{variant:"filled",color:"indigo",size:45,component:m,to:"https://www.instagram.com/smk_yatindo/",target:"_blank",children:e.jsx(J,{size:30})}),e.jsx(D,{variant:"filled",color:"indigo",size:45,component:m,to:"https://youtube.com/@smp-smktintaemasyatindo9557?si=ZPPqAkG4TXplUr0g",target:"_blank",children:e.jsx(U,{size:30})}),e.jsx(P,{w:130,h:45,ml:20,variant:"gradient",gradient:{from:"cyan",to:"indigo"},color:"grape",component:m,to:"/ppdb/auth/login",children:"Masuk"})]})]}),e.jsx(n,{sx:{display:`${!t&&"none"}`},children:e.jsx(h,{p:0,radius:"25px",children:e.jsx(z,{src:"/smk-1.jpg",width:480,className:"hover:brightness-75 transition  duration-300 ease-in-out"})})})]})}),e.jsx(V,{}),e.jsx(Q,{}),e.jsx(Z,{})]})}),e.jsx("footer",{style:{paddingBlock:"20px",justifyContent:"center",display:"flex",alignItems:"center",fontSize:`${r?"":"10px"}`,borderTop:`1px solid ${i?"rgba(60,60,60, 60)":"rgba(240,240,240, 240)"}`},children:"© 2023 D'Coders TKJ Yatindo. All Rights Reserved"})]})};export{de as default};
