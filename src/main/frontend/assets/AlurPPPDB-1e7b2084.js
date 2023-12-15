import{k as K,j as e,aL as V,S as E,B as r,c as i,p as W,m as le,aj as ie,aA as z,r as y,b as de,l as G,o as ce,O as ue,f as I,D as L,$ as Q,aG as J,aI as pe,T as xe,G as he,A as U,aM as ge,aN as je,_ as d}from"./index-c1683f52.js";import{G as me}from"./getAlur-18604aab.js";import{T as X}from"./tiptapInput-86dc94b1.js";import{S as Y}from"./Select-596909b4.js";import{T as R}from"./tiptapOutput-bfdefc50.js";import{A as u,C as be}from"./index.esm-11a21e8a.js";import"./index.esm-9951076c.js";import"./createReactComponent-7e6ec58e.js";import"./Tooltip-82c79980.js";import"./SimpleGrid-248c688e.js";const fe=async t=>(await K.post("/v1/admin/alur-ppdb/post",t)).data,Ae=async t=>(await K.delete("/v1/admin/alur-ppdb/delete?id="+t.id)).data,ke=async t=>{const n={title:t.title,content:t.content};return(await K.put("/v1/admin/alur-ppdb/update?dataId="+t.id,n)).data},Ce=({opened:t,close:n,title:p,setTitle:x,descAlurPPDB:h,grade:g,setGrade:k,setDescAlurPPDB:j,tambahALurHandler:C,createAlurMutation:D})=>e.jsx(V,{size:"70rem",onClose:n,opened:t,title:"Tambah Alur Pendaftaran PPDB",withFooter:!0,onAccept:{acceptFn:C,titleAccept:"Tambah"},loading:D.status==="pending",children:e.jsxs(E,{p:20,pb:"6rem",children:[e.jsxs(r,{children:[e.jsx(i,{align:"left",weight:"bold",children:"Nama"}),e.jsx(W,{"data-autofocus":!0,value:p,onChange:o=>x(o.target.value)})]}),e.jsxs(r,{children:[e.jsx(i,{align:"left",weight:"bold",children:"Jenjang"}),e.jsx(Y,{data:["SMP","SMK"],value:g,onChange:o=>k(o)})]}),e.jsxs(r,{children:[e.jsx(i,{align:"left",weight:"bold",children:"Deskripsi Keterangan"}),e.jsx(X,{desc:h,setDesc:j})]})]})}),De=({opened:t,close:n,title:p,setTitle:x,descAlurPPDB:h,setDescAlurPPDB:g,editAlurHandler:k,editAlurMutation:j,setIdAlur:C,grade:D,setGrade:o})=>e.jsx(V,{size:"70rem",onClose:()=>{n(),g(""),x(""),C("")},opened:t,title:"Ubah Alur Pendaftaran PPDB",withFooter:!0,onAccept:{acceptFn:k,titleAccept:"Ubah"},loading:j.status==="pending",children:e.jsxs(E,{p:20,pb:"6rem",children:[e.jsxs(r,{children:[e.jsx(i,{align:"left",weight:"bold",children:"Nama"}),e.jsx(W,{value:p,onChange:m=>x(m.target.value)})]}),e.jsxs(r,{children:[e.jsx(i,{align:"left",weight:"bold",children:"Jenjang"}),e.jsx(Y,{data:["SMP","SMK"],value:D,onChange:m=>o(m)})]}),e.jsxs(r,{children:[e.jsx(i,{align:"left",weight:"bold",children:"Deskripsi Keterangan"}),e.jsx(X,{desc:h,setDesc:g})]})]})}),Ge=()=>{var O,$;const t=le(),n=ie(),[p,{open:x,close:h}]=z(!1),[g,{open:k,close:j}]=z(!1),[C,{open:D,close:o}]=z(!1),[m,c]=y.useState(null),[v,l]=y.useState(""),[T,S]=y.useState(""),[F,P]=y.useState(""),{data:b,isError:Z,isLoading:_,refetch:q}=de({queryKey:["get_all_alur"],queryFn:me}),B=G({mutationFn:fe}),M=G({mutationFn:Ae}),H=G({mutationFn:ke}),ee=s=>{B.mutate(s,{onSuccess:()=>{d.success("Data berhasil ditambahkan"),l(""),P(""),S(""),h(),n.invalidateQueries({queryKey:["get_all_alur"]})},onError:a=>{var w;((w=a==null?void 0:a.response)==null?void 0:w.status)===400?d.error("Data tidak boleh kosong"):d.error("Gagal membuat alur")}})},se=s=>{H.mutate(s,{onSuccess:()=>{d.success("Data berhasil diubah"),c(""),l(""),P(""),S(""),j(),q(),n.invalidateQueries({queryKey:["get_all_alur"]})},onError:a=>{var w;((w=a==null?void 0:a.response)==null?void 0:w.status)===400?d.error("Data tidak boleh kosong"):d.error("Gagal mengubah alur")}})},ae=s=>{M.mutate(s,{onSuccess:()=>{d.success("Data berhasil dihapus"),o(),q(),c(null),l("")},onError:()=>{d.error("Gagal menghapus alur pendaftaran")}})};if(Z)return e.jsx("h1",{children:"Terjadi Kesalahan"});const te=()=>{ee({content:T,title:v,grade:F})};function ne(s){ae({id:s})}function re(){se({content:T,id:m,title:v,grade:F})}function N({propss:s,data:a}){return e.jsxs(be,{children:[e.jsx(u.Control,{...s,className:"font-bold"}),e.jsxs("div",{style:{paddingInline:"16px",display:"flex",gap:"8px"},children:[e.jsx(U,{color:"brand-yatindo",variant:"filled",size:40,radius:100,onClick:()=>{c(a.id),l(a.title),S(a.content),k()},children:e.jsx(ge,{size:20})}),e.jsx(U,{color:"brand-yatindo",variant:"filled",size:40,radius:100,onClick:()=>{D(),l(a.title),c(a.id)},children:e.jsx(je,{size:20})})]})]})}const f=(O=b==null?void 0:b.data)==null?void 0:O.filter(s=>s.grade==="SMP").sort((s,a)=>s.id-a.id),A=($=b==null?void 0:b.data)==null?void 0:$.filter(s=>s.grade==="SMK").sort((s,a)=>s.id-a.id);return e.jsxs(ce,{title:"Alur Pendaftaran",children:[e.jsx(ue,{label:"Alur Pendaftaran"}),e.jsxs(E,{mt:40,spacing:"2rem",className:"style-box max-w-[70rem] mx-auto",children:[e.jsx(I,{onClick:x,children:"Tambah"}),e.jsxs(r,{children:[e.jsx(i,{weight:600,sx:s=>({fontSize:20,[s.fn.largerThan("sm")]:{fontSize:24}}),children:"SMP"}),e.jsx(L,{})]}),e.jsx(r,{sx:{display:"flex",flexDirection:"column",gap:"16px",paddingBottom:"40px"},children:e.jsx(u,{multiple:!0,variant:"separated",chevronPosition:"left",children:_?e.jsx(Q,{height:80}):(f==null?void 0:f.length)>0?f==null?void 0:f.map(s=>e.jsxs(u.Item,{value:s.id.toString(),mb:20,sx:a=>({boxShadow:"0 4px 10px -6px black",backgroundColor:`${t?a.colors.dark[9]:"white"}`,padding:"0.5rem 0.5rem",border:"0.0625rem solid #dee2e6","&[data-active]":{backgroundColor:t?a.colors.dark[9]:"white",border:"0.0625rem solid #dee2e6"}}),children:[e.jsx(N,{propss:{id:s.id.toString(),children:e.jsx("h2",{children:s.title})},data:s}),e.jsx(u.Panel,{sx:{borderTop:`1px solid ${t?"gray":"#d9d9d9"}`},children:e.jsx(R,{desc:s.content})})]},s.id)):e.jsx(J,{message:"Data kosong"})})}),e.jsxs(r,{children:[e.jsx(i,{weight:600,sx:s=>({fontSize:20,[s.fn.largerThan("sm")]:{fontSize:24}}),children:"SMK"}),e.jsx(L,{})]}),e.jsx(r,{sx:{display:"flex",flexDirection:"column",gap:"16px",paddingBottom:"40px"},children:e.jsx(u,{multiple:!0,variant:"separated",chevronPosition:"left",children:_?e.jsx(Q,{height:80}):(A==null?void 0:A.length)>0?A==null?void 0:A.map(s=>e.jsxs(u.Item,{value:s.id.toString(),mb:20,sx:a=>({boxShadow:"0 4px 10px -6px black",backgroundColor:`${t?a.colors.dark[9]:"white"}`,padding:"0.5rem 0.5rem",border:"0.0625rem solid #dee2e6","&[data-active]":{backgroundColor:t?a.colors.dark[9]:"white",border:"0.0625rem solid #dee2e6"}}),children:[e.jsx(N,{propss:{id:s.id.toString(),children:e.jsx("h2",{children:s.title})},data:s}),e.jsx(u.Panel,{sx:{borderTop:`1px solid ${t?"gray":"#d9d9d9"}`},children:e.jsx(R,{desc:s.content})})]},s.id)):e.jsx(J,{message:"Data kosong"})})}),e.jsx(Ce,{close:h,createAlurMutation:B,descAlurPPDB:T,opened:p,setDescAlurPPDB:S,setTitle:l,grade:F,setGrade:P,tambahALurHandler:te,title:v}),e.jsx(De,{opened:g,close:j,title:v,setTitle:l,descAlurPPDB:T,setDescAlurPPDB:S,setIdAlur:c,editAlurHandler:re,editAlurMutation:H})]}),e.jsxs(pe,{centered:!0,closeOnEscape:!1,closeOnClickOutside:!1,withCloseButton:!1,opened:C,onClose:()=>{o(),l(""),c(null),P("")},children:[e.jsxs(E,{children:[e.jsx(xe,{order:3,children:"Hapus Alur Pendaftaran"}),e.jsxs(i,{children:["Anda yakin ingin menghapus alur pendaftaran ",v,"?"]})]}),e.jsxs(he,{mt:20,position:"right",children:[e.jsx(I,{disabled:M.status==="pending",variant:"outline",onClick:()=>{o(),l(""),c(null),P("")},children:"Batal"}),e.jsx(I,{variant:"danger",onClick:()=>ne(m),loading:M.status==="pending",children:"Hapus"})]})]})]})};export{Ge as default};
