import{r as x,b as S,q as w,n as C,j as a,o as v,O as E,S as M,$ as y,a0 as T,D as B,a1 as D,a2 as K,a3 as L,a4 as _,V as $}from"./index-7cb0f30c.js";import{F as A}from"./index.esm-de00fa2d.js";import{u as I,g as q,T as G,S as N,a as R,b as z,c as J,d as O}from"./generateQueryParam-993c049b.js";import"./ResponseError-252f1eaa.js";import"./index.esm-fbceaefa.js";import"./DataTable-f3beb093.js";import"./Flex-19fefdff.js";import"./Select-58ec8a31.js";import"./Card-dc02554a.js";import"./Badge-46a18456.js";import"./LoadingOverlay-7dce2269.js";import"./FormWrapper-27f535ba.js";import"./SimpleGrid-44ad311c.js";import"./Image-9a78668e.js";const Q=r=>{const{grade:o}=r;return a.jsx(D,{unstyled:!0,styles:e=>({tab:{...e.fn.focusStyles(),backgroundColor:e.colorScheme==="dark"?e.colors.dark[6]:e.white,color:e.colorScheme==="dark"?"white":e.colors.gray[9],border:"0.1625rem solid #dee2e6",boxShadow:"0 10px 20px -10px rgba(0,0,0,0.2)",cursor:"pointer",fontSize:e.fontSizes.sm,borderRadius:"5px","&:disabled":{cursor:"not-allowed",color:e.colorScheme==="dark"?e.colors.gray[4]:e.colors.gray[8],backgroundColor:e.colorScheme==="dark"?e.colors.dark[6]:e.colors.gray[4]},"&[data-active]":{background:`linear-gradient(45deg, ${o=="SMP"&&"#2A166F"||o=="SMK"&&"#FF6C22"}, ${o=="SMP"&&"#6548DB"||o=="SMK"&&"#ff9f22"})`,borderColor:"green",color:e.white,boxShadow:"0 10px 20px -10px rgba(0,0,0,0.5)"}}}),...r})},h=[{index:1,label:"Pilih Gelombang PPDB",icon:K,content:a.jsx(N,{type:"PEMBELIAN"})},{index:2,label:"Transaksi Pembelian",icon:A,content:a.jsx(R,{})},{index:3,label:"Pilih Jurusan",icon:L,content:a.jsx(z,{})},{index:4,label:"Cetak Kartu Peserta",icon:_,content:a.jsx(J,{})}],ie=()=>{var b,g,m,f;const[r,o]=x.useState({step:1,stagingId:null}),{data:e,isSuccess:i,isFetching:p}=S({queryKey:["get_last_offset_batch"],queryFn:()=>O("PEMBELIAN"),staleTime:0,notifyOnChangeProps:"all"}),{data:c}=S({queryFn:$,queryKey:["session"]}),l=I(r),j=w(),P=C(),u=(g=(b=c==null?void 0:c.data)==null?void 0:b.student)==null?void 0:g.grade;x.useEffect(()=>{o(l==null?void 0:l.initialValues)},[l]),x.useEffect(()=>{if(i){const t=e.data.filter(s=>s.is_done===1);if(t.length>0)if(e.data[e.data.length-1].index!==t[t.length-1].index){const s=t[t.length-1].index+1;d(s.toString())}else d(t[t.length-1].index.toString());else d("1")}},[e,i]);const d=t=>{var n;const s={step:+t,stagingId:(n=e.data.find(k=>k.index===+t))==null?void 0:n.id};o(s),P(`${j.pathname}?${q(s)}`)},F=i&&((m=e==null?void 0:e.data)==null?void 0:m.filter(t=>(t==null?void 0:t.grade)===u));return a.jsxs(v,{title:"Pembelian",children:[a.jsx(E,{label:"Pembelian"}),a.jsx(M,{className:"style-box max-w-[100rem] mx-auto",children:a.jsx(Q,{grade:u,value:`${r.step}`,onTabChange:d,children:a.jsxs(a.Fragment,{children:[a.jsx(a.Fragment,{children:p?a.jsx(y,{mt:40,width:"100%",height:200,visible:!0}):a.jsx(a.Fragment,{children:i&&a.jsx(T,{w:"100%",display:"flex",type:"always",sx:{display:"block"},offsetScrollbars:!0,children:a.jsx(G,{activeTabIndex:+r.step,card:F.map((t,s)=>{var n;return{label:t.name,index:t.index,icon:(n=h[s])==null?void 0:n.icon,is_done:t.is_done===1}})})})})}),a.jsx(B,{my:20}),p?a.jsx(y,{mt:40,width:"100%",height:200,visible:!0}):a.jsx(a.Fragment,{children:(f=h.find(t=>t.index===r.step))==null?void 0:f.content})]})})})]})};export{ie as default};