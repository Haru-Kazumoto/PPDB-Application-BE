import{k as z,j as e,aL as N,S as y,B as o,c as i,p as O,m as se,aj as te,aA as E,r as v,b as ae,l as F,o as ne,O as re,f as B,$ as le,aG as oe,D as q,aI as ie,T as de,G as ce,A as H,aM as ue,aN as pe,_ as c}from"./index-7cb0f30c.js";import{G as he}from"./getAlur-a7735b9c.js";import{T as L}from"./tiptapInput-dac4c74c.js";import{S as Q}from"./Select-58ec8a31.js";import{T as xe}from"./tiptapOutput-471c97c6.js";import{A as w,C as ge}from"./index.esm-74ecb24c.js";import"./index.esm-2b1cd2c0.js";import"./createReactComponent-850715e9.js";import"./Tooltip-9cc1d666.js";import"./SimpleGrid-44ad311c.js";const je=async a=>(await z.post("/v1/admin/alur-ppdb/post",a)).data,me=async a=>(console.log(a),(await z.delete("/v1/admin/alur-ppdb/delete?id="+a.id)).data),fe=async a=>{const n={title:a.title,content:a.content};return(await z.put("/v1/admin/alur-ppdb/update?dataId="+a.id,n)).data},be=({opened:a,close:n,title:p,setTitle:h,descAlurPPDB:x,grade:g,setGrade:f,setDescAlurPPDB:j,tambahALurHandler:b,createAlurMutation:A})=>e.jsx(N,{size:"70rem",onClose:n,opened:a,title:"Tambah Alur Pendaftaran PPDB",withFooter:!0,onAccept:{acceptFn:b,titleAccept:"Tambah"},loading:A.status==="pending",children:e.jsxs(y,{p:20,pb:"6rem",children:[e.jsxs(o,{children:[e.jsx(i,{align:"left",weight:"bold",children:"Nama"}),e.jsx(O,{"data-autofocus":!0,value:p,onChange:r=>h(r.target.value)})]}),e.jsxs(o,{children:[e.jsx(i,{align:"left",weight:"bold",children:"Jenjang"}),e.jsx(Q,{data:["SMP","SMK"],value:g,onChange:r=>f(r)})]}),e.jsxs(o,{children:[e.jsx(i,{align:"left",weight:"bold",children:"Deskripsi Keterangan"}),e.jsx(L,{desc:x,setDesc:j})]})]})}),Ae=({opened:a,close:n,title:p,setTitle:h,descAlurPPDB:x,setDescAlurPPDB:g,editAlurHandler:f,editAlurMutation:j,setIdAlur:b,grade:A,setGrade:r})=>e.jsx(N,{size:"70rem",onClose:()=>{n(),g(""),h(""),b("")},opened:a,title:"Ubah Alur Pendaftaran PPDB",withFooter:!0,onAccept:{acceptFn:f,titleAccept:"Ubah"},loading:j.status==="pending",children:e.jsxs(y,{p:20,pb:"6rem",children:[e.jsxs(o,{children:[e.jsx(i,{align:"left",weight:"bold",children:"Nama"}),e.jsx(O,{value:p,onChange:m=>h(m.target.value)})]}),e.jsxs(o,{children:[e.jsx(i,{align:"left",weight:"bold",children:"Jenjang"}),e.jsx(Q,{data:["SMP","SMK"],value:A,onChange:m=>r(m)})]}),e.jsxs(o,{children:[e.jsx(i,{align:"left",weight:"bold",children:"Deskripsi Keterangan"}),e.jsx(L,{desc:x,setDesc:g})]})]})}),Ee=()=>{var _;const a=se(),n=te(),[p,{open:h,close:x}]=E(!1),[g,{open:f,close:j}]=E(!1),[b,{open:A,close:r}]=E(!1),[m,u]=v.useState(null),[C,l]=v.useState(""),[P,k]=v.useState(""),[T,S]=v.useState(""),{data:d,isError:$,isLoading:J,refetch:K}=ae({queryKey:["get_all_alur"],queryFn:he});console.log(d);const G=F({mutationFn:je}),M=F({mutationFn:me}),I=F({mutationFn:fe}),U=s=>{G.mutate(s,{onSuccess:()=>{c.success("Data berhasil ditambahkan"),l(""),S(""),k(""),x(),n.invalidateQueries({queryKey:["get_all_alur"]})},onError:t=>{var D;((D=t==null?void 0:t.response)==null?void 0:D.status)===400?c.error("Data tidak boleh kosong"):c.error("Gagal membuat alur")}})},R=s=>{I.mutate(s,{onSuccess:()=>{c.success("Data berhasil diubah"),u(""),l(""),S(""),k(""),j(),K(),n.invalidateQueries({queryKey:["get_all_alur"]})},onError:t=>{var D;((D=t==null?void 0:t.response)==null?void 0:D.status)===400?c.error("Data tidak boleh kosong"):c.error("Gagal mengubah alur")}})},V=s=>{M.mutate(s,{onSuccess:()=>{c.success("Data berhasil dihapus"),r(),K(),u(null),l("")},onError:()=>{c.error("Gagal menghapus alur pendaftaran")}})};if($)return e.jsx("h1",{children:"Terjadi Kesalahan"});const W=()=>{U({content:P,title:C,grade:T})};function X(s){V({id:s})}function Y(){R({content:P,id:m,title:C,grade:T})}function Z({propss:s,data:t}){return e.jsxs(ge,{children:[e.jsx(w.Control,{...s,className:"font-bold"}),e.jsxs("div",{style:{paddingInline:"16px",display:"flex",gap:"8px"},children:[e.jsx(H,{color:"brand-yatindo",variant:"filled",size:40,radius:100,onClick:()=>{u(t.id),l(t.title),k(t.content),f()},children:e.jsx(ue,{size:20})}),e.jsx(H,{color:"brand-yatindo",variant:"filled",size:40,radius:100,onClick:()=>{A(),l(t.title),u(t.id)},children:e.jsx(pe,{size:20})})]})]})}return e.jsxs(ne,{title:"Alur Pendaftaran",children:[e.jsx(re,{label:"Alur Pendaftaran"}),e.jsxs(y,{mt:40,spacing:"2rem",className:"style-box max-w-[70rem] mx-auto",children:[e.jsx(B,{onClick:h,children:"Tambah"}),e.jsx(o,{sx:{display:"flex",flexDirection:"column",gap:"16px",paddingBottom:"40px"},children:e.jsx(w,{multiple:!0,variant:"separated",chevronPosition:"left",children:J?e.jsx(e.Fragment,{children:e.jsx(le,{height:80})}):d!=null&&d.data&&((_=d==null?void 0:d.data)==null?void 0:_.length)>0?d.data.sort((s,t)=>s.id-t.id).map(s=>e.jsxs(w.Item,{value:s.id.toString(),mb:20,sx:t=>({boxShadow:"0 4px 10px -6px black",backgroundColor:`${a?t.colors.dark[9]:"white"}`,padding:"0.5rem 0.5rem",border:"0.0625rem solid #dee2e6","&[data-active]":{backgroundColor:a?t.colors.dark[9]:"white",border:"0.0625rem solid #dee2e6"}}),children:[e.jsx(Z,{propss:{id:s.id.toString(),children:e.jsx("h2",{children:s.title})},data:s}),e.jsx(w.Panel,{sx:{borderTop:`1px solid ${a?"gray":"#d9d9d9"}`},children:e.jsx(xe,{desc:s.content})})]},s.id)):e.jsx(oe,{message:"Data kosong"})})}),e.jsxs(o,{children:[e.jsx(i,{weight:600,sx:s=>({fontSize:20,[s.fn.largerThan("sm")]:{fontSize:24}}),children:"SMP"}),e.jsx(q,{})]}),e.jsxs(o,{children:[e.jsx(i,{weight:600,sx:s=>({fontSize:20,[s.fn.largerThan("sm")]:{fontSize:24}}),children:"SMK"}),e.jsx(q,{})]}),e.jsx(be,{close:x,createAlurMutation:G,descAlurPPDB:P,opened:p,setDescAlurPPDB:k,setTitle:l,grade:T,setGrade:S,tambahALurHandler:W,title:C}),e.jsx(Ae,{opened:g,close:j,title:C,setTitle:l,descAlurPPDB:P,setDescAlurPPDB:k,setIdAlur:u,editAlurHandler:Y,editAlurMutation:I})]}),e.jsxs(ie,{centered:!0,closeOnEscape:!1,closeOnClickOutside:!1,withCloseButton:!1,opened:b,onClose:()=>{r(),l(""),u(null),S("")},children:[e.jsxs(y,{children:[e.jsx(de,{order:3,children:"Hapus Alur Pendaftaran"}),e.jsxs(i,{children:["Anda yakin ingin menghapus alur pendaftaran ",C,"?"]})]}),e.jsxs(ce,{mt:20,position:"right",children:[e.jsx(B,{disabled:M.status==="pending",variant:"outline",onClick:()=>{r(),l(""),u(null),S("")},children:"Batal"}),e.jsx(B,{variant:"danger",onClick:()=>X(m),loading:M.status==="pending",children:"Hapus"})]})]})]})};export{Ee as default};
