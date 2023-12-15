import{k as K,j as e,aL as V,S as F,B as r,c as i,p as W,m as le,aj as ie,aA as z,r as E,b as de,l as G,o as ce,O as ue,f as I,D as L,$ as Q,aG as J,aI as pe,T as xe,G as he,A as U,aM as ge,aN as je,_ as c}from"./index-9681aaed.js";import{G as me}from"./getAlur-1e850ca5.js";import{T as X}from"./tiptapInput-52fa525e.js";import{S as Y}from"./Select-3c957265.js";import{T as R}from"./tiptapOutput-1f3d9d99.js";import{A as p,C as be}from"./index.esm-cea4228c.js";import"./index.esm-64f2c761.js";import"./createReactComponent-c44e580f.js";import"./Tooltip-c8a506e7.js";import"./SimpleGrid-c1431bb6.js";const fe=async t=>(await K.post("/v1/admin/alur-ppdb/post",t)).data,Ae=async t=>(await K.delete("/v1/admin/alur-ppdb/delete?id="+t.id)).data,ke=async t=>{const n={title:t.title,content:t.content};return(await K.put("/v1/admin/alur-ppdb/update?dataId="+t.id,n)).data},Ce=({opened:t,close:n,title:x,setTitle:h,descAlurPPDB:g,grade:j,setGrade:C,setDescAlurPPDB:m,tambahALurHandler:D,createAlurMutation:v})=>e.jsx(V,{size:"70rem",onClose:n,opened:t,title:"Tambah Alur Pendaftaran PPDB",withFooter:!0,onAccept:{acceptFn:D,titleAccept:"Tambah"},loading:v.status==="pending",children:e.jsxs(F,{p:20,pb:"6rem",children:[e.jsxs(r,{children:[e.jsx(i,{align:"left",weight:"bold",children:"Nama"}),e.jsx(W,{"data-autofocus":!0,value:x,onChange:o=>h(o.target.value)})]}),e.jsxs(r,{children:[e.jsx(i,{align:"left",weight:"bold",children:"Jenjang"}),e.jsx(Y,{data:["SMP","SMK"],value:j,onChange:o=>C(o)})]}),e.jsxs(r,{children:[e.jsx(i,{align:"left",weight:"bold",children:"Deskripsi Keterangan"}),e.jsx(X,{desc:g,setDesc:m})]})]})}),De=({opened:t,close:n,title:x,setTitle:h,descAlurPPDB:g,setDescAlurPPDB:j,editAlurHandler:C,editAlurMutation:m,setIdAlur:D,grade:v,setGrade:o})=>e.jsx(V,{size:"70rem",onClose:()=>{n(),j(""),h(""),D("")},opened:t,title:"Ubah Alur Pendaftaran PPDB",withFooter:!0,onAccept:{acceptFn:C,titleAccept:"Ubah"},loading:m.status==="pending",children:e.jsxs(F,{p:20,pb:"6rem",children:[e.jsxs(r,{children:[e.jsx(i,{align:"left",weight:"bold",children:"Nama"}),e.jsx(W,{value:x,onChange:b=>h(b.target.value)})]}),e.jsxs(r,{children:[e.jsx(i,{align:"left",weight:"bold",children:"Jenjang"}),e.jsx(Y,{data:["SMP","SMK"],value:v,onChange:b=>o(b)})]}),e.jsxs(r,{children:[e.jsx(i,{align:"left",weight:"bold",children:"Deskripsi Keterangan"}),e.jsx(X,{desc:g,setDesc:j})]})]})}),Ge=()=>{var O,$;const t=le(),n=ie(),[x,{open:h,close:g}]=z(!1),[j,{open:C,close:m}]=z(!1),[D,{open:v,close:o}]=z(!1),[b,u]=E.useState(null),[S,l]=E.useState(""),[T,P]=E.useState(""),[y,d]=E.useState(""),{data:f,isError:Z,isLoading:_,refetch:q}=de({queryKey:["get_all_alur"],queryFn:me}),B=G({mutationFn:fe}),M=G({mutationFn:Ae}),H=G({mutationFn:ke}),ee=s=>{B.mutate(s,{onSuccess:()=>{c.success("Data berhasil ditambahkan"),l(""),d(""),P(""),g(),n.invalidateQueries({queryKey:["get_all_alur"]})},onError:a=>{var w;((w=a==null?void 0:a.response)==null?void 0:w.status)===400?c.error("Data tidak boleh kosong"):c.error("Gagal membuat alur")}})},se=s=>{H.mutate(s,{onSuccess:()=>{c.success("Data berhasil diubah"),u(""),l(""),d(""),P(""),m(),q(),n.invalidateQueries({queryKey:["get_all_alur"]})},onError:a=>{var w;((w=a==null?void 0:a.response)==null?void 0:w.status)===400?c.error("Data tidak boleh kosong"):c.error("Gagal mengubah alur")}})},ae=s=>{M.mutate(s,{onSuccess:()=>{c.success("Data berhasil dihapus"),o(),q(),u(null),l("")},onError:()=>{c.error("Gagal menghapus alur pendaftaran")}})};if(Z)return e.jsx("h1",{children:"Terjadi Kesalahan"});const te=()=>{ee({content:T,title:S,grade:y})};function ne(s){ae({id:s})}function re(){se({content:T,id:b,title:S,grade:y})}function N({propss:s,data:a}){return e.jsxs(be,{children:[e.jsx(p.Control,{...s,className:"font-bold"}),e.jsxs("div",{style:{paddingInline:"16px",display:"flex",gap:"8px"},children:[e.jsx(U,{color:"brand-yatindo",variant:"filled",size:40,radius:100,onClick:()=>{u(a.id),l(a.title),P(a.content),d(a.grade),C()},children:e.jsx(ge,{size:20})}),e.jsx(U,{color:"brand-yatindo",variant:"filled",size:40,radius:100,onClick:()=>{v(),d(a.grade),l(a.title),u(a.id)},children:e.jsx(je,{size:20})})]})]})}const A=(O=f==null?void 0:f.data)==null?void 0:O.filter(s=>s.grade==="SMP").sort((s,a)=>s.id-a.id),k=($=f==null?void 0:f.data)==null?void 0:$.filter(s=>s.grade==="SMK").sort((s,a)=>s.id-a.id);return e.jsxs(ce,{title:"Alur Pendaftaran",children:[e.jsx(ue,{label:"Alur Pendaftaran"}),e.jsxs(F,{mt:40,spacing:"2rem",className:"style-box max-w-[70rem] mx-auto",children:[e.jsx(I,{onClick:h,children:"Tambah"}),e.jsxs(r,{children:[e.jsx(i,{weight:600,sx:s=>({fontSize:20,[s.fn.largerThan("sm")]:{fontSize:24}}),children:"SMP"}),e.jsx(L,{})]}),e.jsx(r,{sx:{display:"flex",flexDirection:"column",gap:"16px",paddingBottom:"40px"},children:e.jsx(p,{multiple:!0,variant:"separated",chevronPosition:"left",children:_?e.jsx(Q,{height:80}):(A==null?void 0:A.length)>0?A==null?void 0:A.map(s=>e.jsxs(p.Item,{value:s.id.toString(),mb:20,sx:a=>({boxShadow:"0 4px 10px -6px black",backgroundColor:`${t?a.colors.dark[9]:"white"}`,padding:"0.5rem 0.5rem",border:"0.0625rem solid #dee2e6","&[data-active]":{backgroundColor:t?a.colors.dark[9]:"white",border:"0.0625rem solid #dee2e6"}}),children:[e.jsx(N,{propss:{id:s.id.toString(),children:e.jsx("h2",{children:s.title})},data:s}),e.jsx(p.Panel,{sx:{borderTop:`1px solid ${t?"gray":"#d9d9d9"}`},children:e.jsx(R,{desc:s.content})})]},s.id)):e.jsx(J,{message:"Data kosong"})})}),e.jsxs(r,{children:[e.jsx(i,{weight:600,sx:s=>({fontSize:20,[s.fn.largerThan("sm")]:{fontSize:24}}),children:"SMK"}),e.jsx(L,{})]}),e.jsx(r,{sx:{display:"flex",flexDirection:"column",gap:"16px",paddingBottom:"40px"},children:e.jsx(p,{multiple:!0,variant:"separated",chevronPosition:"left",children:_?e.jsx(Q,{height:80}):(k==null?void 0:k.length)>0?k==null?void 0:k.map(s=>e.jsxs(p.Item,{value:s.id.toString(),mb:20,sx:a=>({boxShadow:"0 4px 10px -6px black",backgroundColor:`${t?a.colors.dark[9]:"white"}`,padding:"0.5rem 0.5rem",border:"0.0625rem solid #dee2e6","&[data-active]":{backgroundColor:t?a.colors.dark[9]:"white",border:"0.0625rem solid #dee2e6"}}),children:[e.jsx(N,{propss:{id:s.id.toString(),children:e.jsx("h2",{children:s.title})},data:s}),e.jsx(p.Panel,{sx:{borderTop:`1px solid ${t?"gray":"#d9d9d9"}`},children:e.jsx(R,{desc:s.content})})]},s.id)):e.jsx(J,{message:"Data kosong"})})}),e.jsx(Ce,{close:g,createAlurMutation:B,descAlurPPDB:T,opened:x,setDescAlurPPDB:P,setTitle:l,grade:y,setGrade:d,tambahALurHandler:te,title:S}),e.jsx(De,{grade:y,setGrade:d,opened:j,close:m,title:S,setTitle:l,descAlurPPDB:T,setDescAlurPPDB:P,setIdAlur:u,editAlurHandler:re,editAlurMutation:H})]}),e.jsxs(pe,{centered:!0,closeOnEscape:!1,closeOnClickOutside:!1,withCloseButton:!1,opened:D,onClose:()=>{o(),l(""),u(null),d("")},children:[e.jsxs(F,{children:[e.jsx(xe,{order:3,children:"Hapus Alur Pendaftaran"}),e.jsxs(i,{children:["Anda yakin ingin menghapus alur pendaftaran ",S,"?"]})]}),e.jsxs(he,{mt:20,position:"right",children:[e.jsx(I,{disabled:M.status==="pending",variant:"outline",onClick:()=>{o(),l(""),u(null),d("")},children:"Batal"}),e.jsx(I,{variant:"danger",onClick:()=>ne(b),loading:M.status==="pending",children:"Hapus"})]})]})]})};export{Ge as default};
