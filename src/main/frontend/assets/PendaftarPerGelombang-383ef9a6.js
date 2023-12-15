import{k as x,d as ae,e as te,m as se,aO as A,r as L,aA as ne,b as y,l as $,j as a,L as G,A as re,o as oe,O as le,S as N,bM as ce,c as r,P as O,B as g,$ as p,T as de,G as q,f,aI as ie,_ as z}from"./index-e253d264.js";import{D as he,c as ue}from"./DataTable-7a7ddedc.js";import{s as ge}from"./statusValue-1ba5fc7f.js";import{B as pe}from"./Badge-379626d7.js";import{c as xe}from"./createReactComponent-f17f1add.js";import"./Flex-4914fec0.js";import"./Select-d0d7a6fd.js";import"./Card-e842cb8c.js";var me=xe("trash","IconTrash",[["path",{d:"M4 7l16 0",key:"svg-0"}],["path",{d:"M10 11l0 6",key:"svg-1"}],["path",{d:"M14 11l0 6",key:"svg-2"}],["path",{d:"M5 7l1 12a2 2 0 0 0 2 2h8a2 2 0 0 0 2 -2l1 -12",key:"svg-3"}],["path",{d:"M9 7v-3a1 1 0 0 1 1 -1h4a1 1 0 0 1 1 1v3",key:"svg-4"}]]);const be=async s=>(await x.get("/v1/admin/registration-batch/get?id="+s)).data,je=async s=>{ae.get(te+"/public/get-student-to-excel?batchId="+s,{responseType:"blob"}).then(n=>{n.headers["Content-Disposition"];const m=window.URL.createObjectURL(new Blob([n.data])),c=document.createElement("a");c.href=m,c.setAttribute("download","student_data.xls"),document.body.appendChild(c),c.click()}).catch(n=>{console.error("Gagal mengunduh file Excel:",n)})},we=async s=>(await x.get(`/v1/admin/registration-batch/get-students?batchId=${s}&page=0&size=1000`)).data,ye=async s=>(await x.get("/v1/admin/registration-batch/count-data?batchId="+s)).data,fe=async s=>(await x.delete("/v1/admin/registration-batch/delete-student-from-batch?studentId="+s)).data,Fe=()=>{var I,M,E,F,_,C,P,T;const s=se(),{gelombangId:n}=A(),[m,c]=L.useState(""),{tipeGelombang:S}=A(),[R,{close:k,open:H}]=ne(),[h,B]=L.useState(null),{data:d,isLoading:b}=y({queryKey:["get_total_pendaftar_batch"],queryFn:()=>ye(n)}),V=$({mutationFn:je}),v=$({mutationFn:fe}),K=e=>{V.mutate(e,{onSuccess:t=>{z.success("Success")},onError:t=>z.error("Gagal")})},{data:o,isFetching:D,isError:U,error:J}=y({queryKey:["get_all_student_by_batch_id"],queryFn:()=>we(n)}),{data:i,isLoading:W}=y({queryKey:["get_gelombang_by_id"],queryFn:()=>be(n)}),Q=()=>{h.id&&v.mutate(h.id,{onSuccess:e=>console.log(e),onError:e=>console.log({err:e})})},X=(I=o==null?void 0:o.data)==null?void 0:I.totalElements,Y=(M=o==null?void 0:o.data)==null?void 0:M.totalPages,j=(F=(E=o==null?void 0:o.data)==null?void 0:E.content)==null?void 0:F.map(e=>({id:(e==null?void 0:e.id)??1,nama:(e==null?void 0:e.name)??"-",noWa:(e==null?void 0:e.phone)??"-",status:(e==null?void 0:e.status)??null,tanggalMendaftar:(e==null?void 0:e.registration_Date)??null})),w=j==null?void 0:j.filter(e=>e),Z=w==null?void 0:w.filter(e=>e.nama.toLowerCase().includes(m.toLowerCase())),l=ue(),ee=[l.accessor(e=>e.id,{id:"Id",cell:e=>e.row.index+1,header:()=>a.jsx("span",{children:"ID"})}),l.accessor(e=>e.nama,{id:"Nama",cell:e=>a.jsx("span",{style:{whiteSpace:"nowrap"},children:e.getValue()}),header:()=>a.jsx("span",{children:"Nama"})}),l.accessor(e=>e.noWa,{id:"No Telepon",cell:e=>e.getValue(),header:()=>a.jsx("span",{children:"No. Telepon"})}),l.accessor(e=>e.tanggalMendaftar,{id:"Tanggal Mendaftar",cell:e=>{const t=e.getValue(),u=t&&new Date(t);return t?u.toLocaleDateString("id-ID",{year:"numeric",month:"short",day:"numeric"}):"-"},header:()=>a.jsx("span",{style:{whiteSpace:"nowrap"},children:"Tanggal Mendaftar"})}),l.accessor(e=>e.status,{id:"Status",cell:e=>{const t=e.getValue(),u=ge[t];return u?a.jsx(pe,{size:"lg",color:u.color,bg:!s&&"#dcfce2",children:u.value}):"-"},header:()=>a.jsx("span",{style:{whiteSpace:"nowrap"},children:"Status"})}),l.display({id:"Detail",cell:e=>{const t=e.row.original.id;return a.jsx(G,{to:`/ppdb/main/pendaftar-ppdb/${S}/${n}/${t}`,className:"bg-blue-600 px-4 py-[3px] no-underline text-white rounded-full font-bold",children:"Detail"})},header:()=>a.jsx("span",{children:"Detail"})}),l.display({id:"Aksi",cell:e=>{const t=e.row.original;return a.jsx(re,{color:"red",variant:"filled",onClick:()=>{t!=null&&t.id&&(t!=null&&t.nama)&&(H(),B({id:t==null?void 0:t.id,name:t==null?void 0:t.nama}))},children:a.jsx(me,{})})},header:()=>a.jsx("span",{children:"Aksi"})})];return a.jsxs(oe,{title:"Seleksi Gelombang",children:[a.jsx(le,{label:"Seleksi Gelombang"}),a.jsxs(N,{mt:20,children:[!D&&a.jsxs(G,{to:`/ppdb/main/pendaftar-ppdb/${S}`,className:"text-xl no-underline font-bold  flex  items-center gap-2 w-fit cursor-pointer",children:[a.jsx(ce,{color:`${s?"#9b87de":"#2A166F"}`}),a.jsx(r,{color:`${s?"#9b87de":"#2A166F"}`,children:"Kembali"})]}),a.jsxs(O,{withBorder:!0,p:"md",radius:"md",bg:"linear-gradient(to left bottom, #6952ba, #160942)",children:[a.jsx(g,{children:W?a.jsx(p,{height:35,w:400}):a.jsx(de,{c:"white",order:2,children:(_=i==null?void 0:i.data)==null?void 0:_.name})}),a.jsxs(g,{bg:`${s?"#1A1B1E":"#FFFFFF"}`,sx:{padding:"0.5rem 1.5rem",marginTop:"1rem",display:"flex",gap:"2rem",borderRadius:"10px"},children:[a.jsxs(g,{children:[a.jsx(r,{weight:"bold",align:"center",children:"Jumlah Pendaftar"}),b?a.jsx(p,{height:30}):a.jsxs(r,{c:`${s?"white":"black"}`,weight:"bold",size:"xl",align:"center",children:[(C=d==null?void 0:d.data)==null?void 0:C.totalStudents," Orang"]})]}),a.jsxs(g,{children:[a.jsx(r,{weight:"bold",align:"center",children:"Jumlah Penerimaan"}),b?a.jsx(p,{height:30}):a.jsxs(r,{c:`${s?"white":"black"}`,weight:"bold",size:"xl",align:"center",children:[(P=i==null?void 0:i.data)==null?void 0:P.max_quota," Orang"]})]}),a.jsxs(g,{children:[a.jsx(r,{weight:"bold",align:"center",children:"Peserta Diterima"}),b?a.jsx(p,{height:30}):a.jsxs(r,{c:`${s?"white":"black"}`,weight:"bold",size:"xl",align:"center",children:[(T=d==null?void 0:d.data)==null?void 0:T.studentAccepted," Orang"]})]})]})]}),a.jsxs(O,{withBorder:!0,p:"md",radius:"md",children:[a.jsxs(q,{mb:20,children:[a.jsx(r,{sx:{flex:1},size:"lg",weight:500,mb:10,children:"Data Pendaftar"}),a.jsx(f,{onClick:()=>K(n),children:"Export Excel"})]}),U?a.jsx(r,{children:J.message}):a.jsx(he,{data:Z,canExpand:()=>!0,columns:ee,loading:D,useSearchInput:!0,noCard:!0,usePagination:!1,onSearch:e=>c(e.toLowerCase()),totalRecords:X,pagination:{pageIndex:0,pageSize:1e3},pageCount:Y})]})]}),a.jsx(ie,{centered:!0,closeOnClickOutside:!1,closeOnEscape:!1,withCloseButton:!1,opened:R,onClose:()=>{k(),B(null)},title:a.jsx(r,{fz:25,fw:600,children:"Hapus Pendaftar"}),children:a.jsxs(N,{children:[a.jsxs(r,{children:["Anda yakin ingin menghapus pendaftar ",h==null?void 0:h.name]}),a.jsxs(q,{position:"right",children:[a.jsx(f,{variant:"outline",onClick:k,children:"Batal"}),a.jsx(f,{color:"red",type:"button",loading:v.status==="pending",onClick:()=>Q(),children:"Hapus"})]})]})})]})};export{Fe as default};
