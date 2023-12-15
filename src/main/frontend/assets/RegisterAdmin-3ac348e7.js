import{R as c,a2 as x,j as e,S as d,w as m,b as p,q as h,g as f,t as g,P as j,B as u,v as b,i as w,L as l,h as k,I as v,_ as N}from"./index-25fc8057.js";import{S}from"./SideAuthLayout-1f5b4bf5.js";import{R as A}from"./ResponseError-cf042987.js";import{P as R}from"./PasswordInput-2f07e97e.js";import{F as y}from"./FormWrapper-7b64f918.js";import{a as F}from"./registration-014904ab.js";const P=c.forwardRef((t,i)=>{var n,a,o;const{register:r,formState:{errors:s}}=x();return e.jsxs(d,{children:[e.jsx(m,{withAsterisk:!0,label:"Nomor Whatsapp/Username",required:!0,error:s.username&&e.jsx("div",{children:(n=s.username)==null?void 0:n.message}),...r("username",{required:{value:!0,message:"dibutuhkan"}})}),e.jsx(m,{error:s.fullname&&e.jsx("div",{children:(a=s.fullname)==null?void 0:a.message}),...r("fullname",{required:{value:!0,message:"dibutuhkan"}}),label:"Nama Lengkap"}),e.jsx(R,{withAsterisk:!0,label:"Password",required:!0,error:s.password&&e.jsx("div",{children:(o=s.password)==null?void 0:o.message}),...r("password",{required:{value:!0,message:"dibutuhkan"}})})]})}),_=()=>{const{md:t}=p(),i=h({mutationFn:F}),r=f(),s=n=>{i.mutate(n,{onSuccess:a=>{N.success("Sukses mendaftarkan, sekarang anda bisa login! "),r("/ppdb/auth/login")},onError:a=>A(a)})};return e.jsxs(g,{title:"Daftar User Admin",children:[e.jsxs(j,{pt:`${t?0:"70px"}`,className:"flex  min-h-[100vh]",children:[e.jsx(u,{className:"flex-[2] p-[0_1rem_] flex flex-col overflow-y-auto min-h-[87vh]  items-center",children:e.jsxs(u,{w:`${t?"30rem":"20rem"}`,className:"py-[2rem] mx-auto mt-20 ",children:[e.jsx(b,{align:"center",children:"Daftar User Admin"}),e.jsx(y,{id:"form-registeradmin",onSubmit:s,children:e.jsxs(d,{className:"mt-10",children:[e.jsx(P,{}),e.jsxs(w,{sx:{display:"flex",justifyContent:"space-between",flexDirection:`${t?"row":"column"}`},children:[e.jsx(l,{to:"/ppdb/auth/login",className:"text-[#103C6F] text-center",children:"Sudah punya akun ?"}),e.jsx(l,{to:"https://wa.me/6281380908008",className:"text-[#103C6F] text-center",children:"Butuh bantuan ?"})]}),e.jsx(k,{type:"submit",loading:i.isLoading,children:"Daftar"})]})})]})}),e.jsx(S,{})]}),e.jsx(v,{position:"top-center",reverseOrder:!1})]})};export{_ as default};
