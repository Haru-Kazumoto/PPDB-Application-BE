import{r as b,b as F,q as I,n as L,j as a,o as A,O as $,S as G,$ as k,a0 as N,D as q,a1 as R,V as z,a2 as M,a3 as J,a4 as w}from"./index-b070c93a.js";import{F as C}from"./index.esm-76cd4960.js";import{u as O,g as Q,T as E,a as V,S as K,b as T,c as H,d as v}from"./generateQueryParam-2b40c420.js";import"./ResponseError-ce4b4c5a.js";import"./imageUtils-040c21d1.js";import"./index.esm-7ed69567.js";import"./DataTable-67ed3278.js";import"./Flex-4e6f41b4.js";import"./Select-9229d143.js";import"./Card-33bd5c15.js";import"./Badge-b6f62ad9.js";import"./LoadingOverlay-8626e438.js";import"./FormWrapper-d4f53d37.js";import"./SimpleGrid-737d8ee7.js";import"./Image-5f23b392.js";const U=s=>{const{grade:r}=s;return a.jsx(R,{unstyled:!0,styles:t=>({tab:{...t.fn.focusStyles(),backgroundColor:t.colorScheme==="dark"?t.colors.dark[6]:t.white,color:t.colorScheme==="dark"?"white":t.colors.gray[9],border:"0.1625rem solid #dee2e6",boxShadow:"0 10px 20px -10px rgba(0,0,0,0.2)",cursor:"pointer",fontSize:t.fontSizes.sm,borderRadius:"5px","&:disabled":{cursor:"not-allowed",color:t.colorScheme==="dark"?t.colors.gray[4]:t.colors.gray[8],backgroundColor:t.colorScheme==="dark"?t.colors.dark[6]:t.colors.gray[4]},"&[data-active]":{background:`linear-gradient(45deg, ${r=="SMP"&&"#2A166F"||r=="SMK"&&"#FF6C22"}, ${r=="SMP"&&"#6548DB"||r=="SMK"&&"#ff9f22"})`,borderColor:"green",color:t.white,boxShadow:"0 10px 20px -10px rgba(0,0,0,0.5)"}}}),...s})},xe=()=>{var g,f,P,y,h,j;const[s,r]=b.useState({step:1,stagingId:null}),{data:t,isSuccess:d,isFetching:p}=F({queryKey:["get_last_offset_batch"],queryFn:()=>V("PEMBELIAN"),staleTime:0,notifyOnChangeProps:"all"}),{data:i}=F({queryFn:z,queryKey:["session"]});console.log((g=i==null?void 0:i.data)==null?void 0:g.student);const u=[{index:1,label:"Pilih Gelombang PPDB",icon:M,content:a.jsx(K,{type:"PEMBELIAN"})},{index:2,label:"Transaksi Pembelian",icon:C,content:a.jsx(T,{})},{index:3,label:"Pilih Jurusan",icon:J,content:a.jsx(H,{})},{index:4,label:"Cetak Kartu Peserta",icon:w,content:a.jsx(v,{})}],m=[{index:1,label:"Pilih Gelombang PPDB",icon:M,content:a.jsx(K,{type:"PEMBELIAN"})},{index:2,label:"Transaksi Pembelian",icon:C,content:a.jsx(T,{})},{index:3,label:"Cetak Kartu Peserta",icon:w,content:a.jsx(v,{})}],c=O(s),B=I(),D=L(),l=(P=(f=i==null?void 0:i.data)==null?void 0:f.student)==null?void 0:P.grade;b.useEffect(()=>{r(c==null?void 0:c.initialValues)},[c]),b.useEffect(()=>{if(d){const e=t.data.filter(o=>o.is_done===1);if(e.length>0)if(t.data[t.data.length-1].index!==e[e.length-1].index){const o=e[e.length-1].index+1;x(o.toString())}else x(e[e.length-1].index.toString());else x("1")}},[t,d]);const x=e=>{var n;const o={step:+e,stagingId:(n=t.data.find(_=>_.index===+e))==null?void 0:n.id};r(o),D(`${B.pathname}?${Q(o)}`)},S=d&&((y=t==null?void 0:t.data)==null?void 0:y.filter(e=>(e==null?void 0:e.grade)===l));return a.jsxs(A,{title:"Pembelian",children:[a.jsx($,{label:"Pembelian"}),a.jsx(G,{className:"style-box max-w-[100rem] mx-auto",children:a.jsx(U,{grade:l,value:`${s.step}`,onTabChange:x,children:a.jsxs(a.Fragment,{children:[a.jsx(a.Fragment,{children:p?a.jsx(k,{mt:40,width:"100%",height:200,visible:!0}):a.jsx(a.Fragment,{children:d&&a.jsxs(N,{w:"100%",display:"flex",type:"always",sx:{display:"block"},offsetScrollbars:!0,children:[l==="SMK"&&a.jsx(E,{activeTabIndex:+s.step,card:S.map((e,o)=>{var n;return{label:e.name,index:e.index,icon:(n=u[o])==null?void 0:n.icon,is_done:e.is_done===1}})}),l==="SMP"&&a.jsx(E,{activeTabIndex:+s.step,card:S.map((e,o)=>{var n;return{label:e.name,index:e.index,icon:(n=m[o])==null?void 0:n.icon,is_done:e.is_done===1}})})]})})}),a.jsx(q,{my:20}),p?a.jsx(k,{mt:40,width:"100%",height:200,visible:!0}):a.jsxs(a.Fragment,{children:[l=="SMK"&&((h=u.find(e=>e.index===s.step))==null?void 0:h.content),l=="SMP"&&((j=m.find(e=>e.index===s.step))==null?void 0:j.content)]})]})})})]})};export{xe as default};