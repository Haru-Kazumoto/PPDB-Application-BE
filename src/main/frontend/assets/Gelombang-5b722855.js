import{k as S,j as e,aL as se,S as ne,p as P,ah as q,bE as K,G as y,f as D,aA as z,N as re,b as oe,l as R,P as Z,bF as ke,B as le,c as x,L as be,bG as L,A as ee,bH as xe,aI as de,T as ue,_ as h,aD as je,m as ge,aO as De,a as we,o as fe,$ as Te,aM as Ce,aN as ye}from"./index-b070c93a.js";import{b as Ie}from"./index.esm-76cd4960.js";import{A as W,e as Pe,C as Se}from"./index.esm-8084934a.js";import{T as ae}from"./ThemeIcon-bac4a122.js";import{B as ce}from"./Badge-b6f62ad9.js";import{S as _e}from"./Select-9229d143.js";const Ge=async t=>{const{idJalur:i,payloadCreate:s}=t,u={...s,path_id:i};return(await S.post("/v1/admin/registration-batch/post",u)).data},Ue=async t=>(await S.delete("/v1/admin/registration-batch/delete?id="+t.id)).data,ve=async t=>{const{id:i,bank_account:s,bank_name:u,bank_user:k,end_date:j,index:r,max_quota:w,name:o,price:_,start_date:l,batchCode:p}=t,d={bank_account:s,bank_name:u,bank_user:k,start_date:l,end_date:j,index:r,max_quota:w,name:o,price:_,batchCode:p};return(await S.patch("/v1/admin/registration-batch/update?id="+i,d)).data},Be=async t=>(await S.get("/v1/admin/registration-batch/get-batch-by-pathsId?pathsId="+t)).data,Fe=async t=>(await S.post("/v1/user/exam-information/create",t)).data,Ee=async t=>{const{id:i}=t;return(await S.delete("/v1/user/exam-information/delete?id="+i)).data},qe=async t=>{const{id:i,...s}=t;return(await S.patch("/v1/user/exam-information/update?id="+i,s)).data},ze=async t=>(await S.get("/v1/user/exam-information/index?batchId="+t)).data,te=({action:{actionFn:t,label:i},formMantine:s,close:u,loading:k,opened:j,title:r})=>{const w=o=>{t(o)};return e.jsx(se,{onClose:u,opened:j,size:"40rem",title:r,withFooter:!1,children:e.jsxs("form",{onSubmit:s.onSubmit(o=>w(o)),children:[e.jsxs(ne,{p:20,pb:"6rem",children:[e.jsx(P,{required:!0,label:"Nama Test Ujian",...s.getInputProps("nama")}),e.jsx(P,{required:!0,label:"Link Test Ujian",...s.getInputProps("link")}),e.jsxs(q,{children:[e.jsx(q.Col,{md:6,children:e.jsx(K,{withAsterisk:!0,"aria-required":"true",label:"Waktu Pendaftaran Dibuka",dropdownType:"modal",...s.getInputProps("waktuDibuka"),clearable:!0})}),e.jsx(q.Col,{md:6,children:e.jsx(K,{required:!0,label:"Waktu Pendaftaran Ditutup",dropdownType:"modal",...s.getInputProps("waktuDitutup"),clearable:!0})})]})]}),e.jsxs(y,{position:"right",sx:o=>({position:"absolute",bottom:0,right:0,left:0,padding:"1rem 4rem",backgroundColor:o.colorScheme==="dark"?"black":"whitesmoke",zIndex:1}),children:[e.jsx(D,{variant:"outline",onClick:()=>u(),disabled:k,children:"Batal"}),e.jsx(D,{type:"submit",loading:k,children:i})]})]})})},Re=({idGelombang:t})=>{const[i,{open:s,close:u}]=z(!1),[k,{open:j,close:r}]=z(!1),[w,{open:o,close:_}]=z(!1),l=re({initialValues:{id:null,link:"",nama:"",waktuDibuka:null,waktuDitutup:null},transformValues:a=>({...a,waktuDibuka:a.waktuDibuka.toISOString(),waktuDitutup:a.waktuDitutup.toISOString()}),validate:{waktuDibuka:a=>a===null?"Tolong masukkan waktu dibuka":null,waktuDitutup:a=>a===null?"Tolong masukkan waktu ditutup":null}}),{data:p,refetch:d}=oe({queryKey:["get_test_ujian"+t],queryFn:()=>ze(t)}),c=R({mutationFn:Fe}),A=R({mutationFn:Ee}),v=R({mutationFn:qe});function V(a){c.mutate(a,{onSuccess:()=>{d(),h.success("Data berhasil ditambahkan"),u(),l.reset()},onError:()=>{h.error("Data gagal ditambahkan")}})}function M(a){const{link:f,nama:b,waktuDibuka:g,waktuDitutup:B}=a;V({link:f,title:b,batchId:t,endDate:B,startDate:g})}function N(a){v.mutate(a,{onSuccess:()=>{d(),r(),h.success("Data berhasil diubah"),l.reset()},onError:()=>{h.error("Data gagal diubah")}})}function Q(a){const{link:f,nama:b,waktuDibuka:g,waktuDitutup:B,id:F}=a;N({id:F,link:f,title:b,endDate:B,startDate:g})}function X(a){A.mutate(a,{onSuccess:()=>{d(),h.success("Data berhasil dihapus"),_(),l.reset()},onError:()=>{h.error("Gagal menghapus data")}})}return e.jsxs(e.Fragment,{children:[(p==null?void 0:p.data)&&(p==null?void 0:p.data.sort((a,f)=>a.id-f.id).map((a,f)=>{const b=a!=null&&a.startDate?new Date(a==null?void 0:a.startDate):null,g=a!=null&&a.endDate?new Date(a==null?void 0:a.endDate):null,F=new Date>=b&&b<=g,J=b?b.toLocaleDateString("id-ID",{year:"numeric",month:"long",day:"numeric",hour:"numeric",minute:"numeric"}).replace(".",":").split(" pukul"):"Invalid Date",$=g?g.toLocaleDateString("id-ID",{year:"numeric",month:"long",day:"numeric",hour:"numeric",minute:"numeric"}).replace(".",":").split(" pukul"):"Invalid Date";return e.jsx(Z,{withBorder:!0,p:"1rem",sx:n=>({backgroundColor:n.colorScheme==="dark"?n.colors.dark[9]:n.colors["brand-smp"][0],display:"flex",justifyContent:"space-between",alignItems:"center"}),children:e.jsxs(y,{align:"start",w:"100%",children:[e.jsxs(y,{sx:{flex:2},children:[e.jsx(ae,{radius:"100%",size:50,mx:"sm",children:e.jsx(ke,{size:30})}),e.jsxs(le,{sx:{flex:2},children:[e.jsx(x,{size:16,weight:"bolder",children:a.title}),e.jsxs(x,{children:[J," – ",$]}),e.jsxs(x,{children:["Link Tes Online : ",e.jsx(x,{target:"_blank",c:"blue",component:be,to:a.link&&a.link,children:a.link&&a.link})]})]})]}),e.jsxs(y,{children:[e.jsx(ce,{variant:"light",color:`${F?"green":"red"}`,size:"lg",children:F?"Dibuka":"Ditutup"}),e.jsxs(L,{withArrow:!0,width:100,position:"left-start",onOpen:()=>{l.setValues({id:a.id,nama:a.title})},onClose:()=>{console.log("close")},children:[e.jsx(L.Target,{children:e.jsx(ee,{variant:"transparent",children:e.jsx(xe,{size:30})})}),e.jsxs(L.Dropdown,{children:[e.jsx(L.Item,{onClick:()=>{j(),l.setValues({id:a.id,nama:a.title,waktuDibuka:b,waktuDitutup:g,link:a.link})},children:"Edit"}),e.jsx(L.Item,{onClick:()=>{o()},children:"Delete"})]})]})]})]})},a!=null&&a.id?a.id:f)})),e.jsx(D,{onClick:()=>s(),children:"Tambah Ujian"}),e.jsx(te,{formMantine:l,action:{label:"Tambah",actionFn:M},close:()=>{u(),l.reset()},loading:c.status==="pending",opened:i,title:"Tambah Tes Ujian"}),e.jsx(te,{formMantine:l,action:{label:"Ubah",actionFn:Q},close:()=>{r(),l.reset()},loading:v.status==="pending",opened:k,title:"Ubah Tes Ujian"}),e.jsxs(de,{centered:!0,closeOnEscape:!1,closeOnClickOutside:!1,withCloseButton:!1,opened:w,onClose:()=>{_(),l.reset()},children:[e.jsx(ue,{order:3,children:"Hapus Tes Ujian"}),e.jsxs(x,{mt:20,children:["Anda yakin ingin menghapus tes ujian ",l.values.nama,"?"]}),e.jsxs(y,{mt:20,position:"right",children:[e.jsx(D,{disabled:A.status==="pending",variant:"outline",onClick:()=>{_(),l.reset()},children:"Batal"}),e.jsx(D,{variant:"danger",onClick:()=>X({id:l.values.id}),loading:A.status==="pending",children:"Hapus"})]})]})]})},ie=({title:t,close:i,opened:s,action:{actionFn:u,label:k},loading:j,formMantine:r})=>{function w(o){u(o)}return e.jsx(se,{onClose:i,opened:s,size:"40rem",title:t,withFooter:!1,children:e.jsxs("form",{onSubmit:r.onSubmit(o=>w(o)),children:[e.jsxs(ne,{p:20,pb:"6rem",children:[e.jsx(P,{required:!0,label:"Nama",...r.getInputProps("nama")}),e.jsx(P,{required:!0,label:"Jumlah Penerimaan",type:"number",...r.getInputProps("jumlahPenerimaan")}),e.jsxs(q,{children:[e.jsx(q.Col,{md:6,children:e.jsx(K,{withAsterisk:!0,"aria-required":"true",label:"Waktu Pendaftaran Dibuka",dropdownType:"modal",...r.getInputProps("waktuDibuka"),clearable:!0})}),e.jsx(q.Col,{md:6,children:e.jsx(K,{required:!0,label:"Waktu Pendaftaran Ditutup",dropdownType:"modal",...r.getInputProps("waktuDitutup"),clearable:!0})})]}),e.jsx(_e,{required:!0,label:"Nama Bank",defaultValue:"CIMB",...r.getInputProps("namaBank"),data:["CIMB","BCA","BRI","BNI","MANDIRI"]}),e.jsx(P,{required:!0,label:"Nomor Rekening",type:"number",...r.getInputProps("nomorRekening")}),e.jsx(P,{required:!0,label:"Nama Pemilik Rekening",...r.getInputProps("namaPemilikRekening")}),e.jsx(je,{required:!0,thousandSeparator:".",decimalSeparator:",",prefix:"Rp. ",customInput:P,placeholder:"Rp. 0",label:"Biaya Pendaftaran",description:"Input Nominal",...r.getInputProps("biayaPendaftaran"),withAsterisk:!0}),e.jsx(P,{label:"Kode Gelombang",required:!0,...r.getInputProps("kodeGelombang")})]}),e.jsxs(y,{position:"right",sx:o=>({position:"absolute",bottom:0,right:0,left:0,padding:"1rem 4rem",backgroundColor:o.colorScheme==="dark"?"black":"whitesmoke",zIndex:1}),children:[e.jsx(D,{variant:"outline",onClick:()=>i(),children:"Batal"}),e.jsx(D,{type:"submit",loading:j,children:k})]})]})})},Me=()=>{var J,$;const t=ge(),{idJalurPendaftaran:i}=De(),s=we(),[u,{open:k,close:j}]=z(!1),[r,{open:w,close:o}]=z(!1),[_,{open:l,close:p}]=z(!1),d=re({initialValues:{id:null,nama:"",jumlahPenerimaan:"",waktuDibuka:null,waktuDitutup:null,namaBank:"",nomorRekening:"",namaPemilikRekening:"",biayaPendaftaran:null,kodeGelombang:""},transformValues:n=>({...n,biayaPendaftaran:+n.biayaPendaftaran.substring(4).replace(/\./g,""),waktuDibuka:n.waktuDibuka.toISOString(),waktuDitutup:n.waktuDitutup.toISOString()}),validate:{waktuDibuka:n=>n===null?"Tolong masukkan waktu dibuka":null,waktuDitutup:n=>n===null?"Tolong masukkan waktu ditutup":null}}),{data:c,isLoading:A,refetch:v}=oe({queryKey:["get_gelombang_by_id_jalur"],queryFn:()=>Be(i)}),V=R({mutationFn:Ge}),M=R({mutationFn:ve}),N=R({mutationFn:Ue});function Q(n){V.mutate(n,{onSuccess:()=>{v(),j(),d.reset(),h.success("Data berhasil ditambahkan")},onError:()=>{h.error("Data gagal ditambahkan")}})}function X(n){M.mutate(n,{onSuccess:()=>{o(),v(),d.reset(),h.success("Data berhasil diubah")},onError:()=>{h.error("Data gagal diubah")}})}function a(n){N.mutate(n,{onSuccess:()=>{p(),v(),h.success("Data berhasil dihapus")},onError:()=>{h.error("Data gagal dihapus")}})}function f(n){const{biayaPendaftaran:m,jumlahPenerimaan:G,nama:T,namaBank:U,namaPemilikRekening:E,nomorRekening:I,waktuDibuka:C,waktuDitutup:O,kodeGelombang:H}=n;Q({idJalur:+i,payloadCreate:{bank_account:I,bank_name:U,bank_user:E,end_date:O,index:1,max_quota:+G,name:T,price:m,start_date:C,batchCode:H}})}function b(n){const{id:m,biayaPendaftaran:G,jumlahPenerimaan:T,nama:U,namaBank:E,namaPemilikRekening:I,nomorRekening:C,waktuDibuka:O,waktuDitutup:H,kodeGelombang:Y}=n;X({id:+m,bank_account:C,bank_name:E,bank_user:I,end_date:H,index:1,max_quota:+T,name:U,price:G,start_date:O,batchCode:Y})}function g(n){a({id:n})}function B({propss:n,data:m}){return e.jsxs(Se,{children:[e.jsx(W.Control,{...n,className:"font-bold"}),e.jsxs("div",{style:{paddingInline:"16px",display:"flex",gap:"8px"},children:[e.jsx(ee,{variant:"filled",color:"brand-smp",size:40,radius:100,onClick:()=>{const{bank_account:G,bank_name:T,bank_user:U,end_date:E,max_quota:I,name:C,price:O,start_date:H,id:Y,batchCode:me}=m,pe=new Date(H),he=new Date(E);d.setValues({id:Y,biayaPendaftaran:"Rp. "+O,jumlahPenerimaan:I+"",kodeGelombang:me,nama:C,namaBank:T,namaPemilikRekening:U,nomorRekening:G,waktuDibuka:pe,waktuDitutup:he}),w()},children:e.jsx(Ce,{size:20})}),e.jsx(ee,{variant:"filled",color:"brand-smp",size:40,radius:100,onClick:()=>{l(),d.setValues({id:m.id})},children:e.jsx(ye,{size:20})})]})]})}const F=e.jsx(e.Fragment,{children:c!=null&&c.data&&((J=c==null?void 0:c.data)==null?void 0:J.length)<1?"":e.jsx(W,{multiple:!0,variant:"separated",chevronPosition:"left",mt:30,children:($=c==null?void 0:c.data)==null?void 0:$.sort((n,m)=>n.id-m.id).map(n=>{const m=n!=null&&n.start_date?new Date(n.start_date):null,G=m?m.toLocaleDateString("id-ID",{year:"numeric",month:"long",day:"numeric",hour:"numeric",minute:"numeric"}).replace(".",":").split(" pukul"):"Invalid Date",T=n!=null&&n.end_date?new Date(n.end_date):null,U=T?T.toLocaleDateString("id-ID",{year:"numeric",month:"long",day:"numeric",hour:"numeric",minute:"numeric"}).replace(".",":").split(" pukul"):"Invalid Date",I=new Date>=m&&m<=T;return e.jsxs(W.Item,{value:n.id.toString(),sx:{boxShadow:"0 4px 10px -6px black",padding:"0 0.5rem",border:"0.0625rem solid #dee2e6",backgroundColor:`${t?s.colors.dark[9]:"white"}`,"&[data-active]":{backgroundColor:t?s.colors.dark[8]:"white",border:"0.0625rem solid #dee2e6"}},children:[e.jsx(B,{propss:{id:n.id.toString(),children:e.jsxs(e.Fragment,{children:[e.jsx(x,{fw:600,size:26,children:n.name}),e.jsxs(y,{mt:5,children:[e.jsx(ae,{variant:"light",size:30,radius:"50%",children:e.jsx(Ie,{size:15})}),e.jsxs(x,{size:16,children:[" ",n.countStudent," Pendaftar"]})]})]})},data:n}),e.jsx(W.Panel,{sx:{borderTop:`1px solid ${t?"gray":"#d9d9d9"}`},children:e.jsxs(ne,{p:"1rem",children:[e.jsxs(Z,{withBorder:!0,p:"1rem",sx:C=>({backgroundColor:C.colorScheme==="dark"?C.colors.dark[9]:C.colors["brand-smp"][0],display:"flex",justifyContent:"space-between",alignItems:"center"}),children:[e.jsxs(y,{children:[e.jsx(ae,{radius:"100%",size:50,mx:"sm",children:e.jsx(Pe,{size:30})}),e.jsxs(le,{children:[e.jsx(x,{size:16,weight:"bolder",children:"Pendaftaran Gelombang"}),e.jsxs(x,{children:[G," – ",U]})]})]}),e.jsx(ce,{variant:"light",color:`${I?"green":"red"}`,size:"lg",children:I?"Dibuka":"Ditutup"})]}),e.jsx(Re,{idGelombang:n.id})]})})]},n.id)})})});return e.jsxs(fe,{title:"Gelombang",children:[e.jsxs(Z,{withBorder:!0,shadow:"sm",radius:"4rem",px:"2.5rem",bg:t?s.colors.dark[8]:"white",sx:{display:"flex",justifyContent:"space-between",padding:"1rem 2rem"},children:[e.jsx(x,{weight:"bold",size:"xl",children:"Daftar Gelombang"}),e.jsx(D,{onClick:()=>k(),children:"Tambah"})]}),A?e.jsx(Te,{height:80}):F,e.jsx(ie,{title:"Tambah Gelombang PPDB",opened:u,close:j,formMantine:d,action:{actionFn:f,label:"Tambah"},loading:V.status==="pending"}),e.jsx(ie,{title:"Ubah Gelombang PPDB",opened:r,formMantine:d,action:{actionFn:b,label:"Ubah"},close:()=>{o(),d.reset()},loading:M.status==="pending"}),e.jsxs(de,{centered:!0,closeOnEscape:!1,closeOnClickOutside:!1,withCloseButton:!1,opened:_,onClose:()=>{p(),d.reset()},children:[e.jsx(ue,{order:3,children:"Hapus Gelombang"}),e.jsxs(x,{mt:20,children:["Anda yakin ingin menghapus gelombang ",d.values.nama,"?"]}),e.jsxs(y,{mt:20,position:"right",children:[e.jsx(D,{disabled:N.status==="pending",variant:"outline",onClick:()=>{p(),d.reset()},children:"Batal"}),e.jsx(D,{variant:"danger",onClick:()=>g(d.values.id),loading:N.status==="pending",children:"Hapus"})]})]})]})};export{Me as default};
