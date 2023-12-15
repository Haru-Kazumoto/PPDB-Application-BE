import{af as D,r as v,ag as Le,ah as Fe,ai as Me,a3 as re,R as _,a5 as Re,aj as Oe,a4 as se,ak as K,al as Ue,am as ze,an as Ge,ao as Ke,a as J,j as e,l as ie,E as Ve,ap as We,aq as Ye,d as $e,I as te,ar as He,$ as de,S as A,as as B,_ as O,at as u,w as f,au as le,y as $,q as Je,av as Qe,t as Xe,b as ue,B as V,T as W,h as Ze,e as ea,g as aa,v as ra,aa as sa,D as ta,x as la,ab as oa,ac as na,ad as ia,ae as da}from"./index-dba9ce5e.js";import{D as M,I as ua,f as ca,R as pa,h as ma,i as ha,j as ga,u as xa,g as ba,S as fa,T as ja,a as _a,F as va,b as ka,c as Pa,d as ya,e as Da}from"./StepPilihJurusan-1cd050fd.js";import{F as Ia}from"./FormWrapper-618247dc.js";import{R as Sa}from"./ResponseError-bd25342d.js";import"./LoadingOverlay-7e2a36f3.js";function Ca({date:r,maxDate:s,minDate:a}){return!(r==null||Number.isNaN(r.getTime())||s&&D(r).isAfter(s,"date")||a&&D(r).isBefore(a,"date"))}function wa(r){const s=new Date(r);return Number.isNaN(s.getTime())||!r?null:s}var Na=Object.defineProperty,Aa=Object.defineProperties,qa=Object.getOwnPropertyDescriptors,U=Object.getOwnPropertySymbols,ce=Object.prototype.hasOwnProperty,pe=Object.prototype.propertyIsEnumerable,oe=(r,s,a)=>s in r?Na(r,s,{enumerable:!0,configurable:!0,writable:!0,value:a}):r[s]=a,S=(r,s)=>{for(var a in s||(s={}))ce.call(s,a)&&oe(r,a,s[a]);if(U)for(var a of U(s))pe.call(s,a)&&oe(r,a,s[a]);return r},R=(r,s)=>Aa(r,qa(s)),Ta=(r,s)=>{var a={};for(var n in r)ce.call(r,n)&&s.indexOf(n)<0&&(a[n]=r[n]);if(r!=null&&U)for(var n of U(r))s.indexOf(n)<0&&pe.call(r,n)&&(a[n]=r[n]);return a};const Ba={valueFormat:"MMMM D, YYYY",fixOnBlur:!0,preserveTime:!0,size:"sm"},me=v.forwardRef((r,s)=>{const a=Le("DateInput",Ba,r),{inputProps:n,wrapperProps:i,value:l,defaultValue:x,onChange:g,clearable:c,clearButtonProps:b,popoverProps:o,getDayProps:m,locale:t,valueFormat:d,dateParser:h,minDate:j,maxDate:P,fixOnBlur:I,onFocus:C,onBlur:Q,onClick:X,readOnly:E,name:he,form:ge,rightSection:xe,unstyled:Z,classNames:be,styles:fe,allowDeselect:ee,preserveTime:je,date:_e,defaultDate:ve,onDateChange:ke}=a,Pe=Ta(a,["inputProps","wrapperProps","value","defaultValue","onChange","clearable","clearButtonProps","popoverProps","getDayProps","locale","valueFormat","dateParser","minDate","maxDate","fixOnBlur","onFocus","onBlur","onClick","readOnly","name","form","rightSection","unstyled","classNames","styles","allowDeselect","preserveTime","date","defaultDate","onDateChange"]),{calendarProps:ye,others:De}=Fe(Pe),z=Me(),Ie=h||(p=>{const k=D(p,d,z.getLocale(t)).toDate();return Number.isNaN(k.getTime())?wa(p):k}),Se=ee!==void 0?ee:c,q=p=>p?D(p).locale(z.getLocale(t)).format(d):"",[y,L,F]=re({value:l,defaultValue:x,finalValue:null,onChange:g}),[Ce,G]=re({value:_e,defaultValue:x||ve,finalValue:null,onChange:ke});v.useEffect(()=>{F&&G(l)},[F,l]);const[we,w]=v.useState(q(y));v.useEffect(()=>{w(q(y))},[z.getLocale(t)]);const[ae,T]=v.useState(!1),Ne=p=>{const k=p.currentTarget.value;if(w(k),k.trim()===""&&c)L(null);else{const N=Ie(k);Ca({date:N,minDate:j,maxDate:P})&&(L(N),G(N))}},Ae=p=>{Q==null||Q(p),T(!1),I&&w(q(y))},qe=p=>{C==null||C(p),T(!0)},Te=p=>{X==null||X(p),T(!0)},Be=p=>R(S({},m==null?void 0:m(p)),{selected:D(y).isSame(p,"day"),onClick:()=>{const k=je?Ge(y,p):p,N=c&&Se&&D(y).isSame(p,"day")?null:k;L(N),!F&&w(q(N)),T(!1)}}),Ee=xe||(c&&y&&!E?_.createElement(Re,S({variant:"transparent",onMouseDown:p=>p.preventDefault(),tabIndex:-1,onClick:()=>{L(null),!F&&w(""),T(!1)},unstyled:Z},b)):null);return Oe(()=>{l!==void 0&&!ae&&w(q(l))},[l]),_.createElement(_.Fragment,null,_.createElement(se.Wrapper,R(S({},i),{__staticSelector:"DateInput"}),_.createElement(K,S({opened:ae,trapFocus:!1,position:"bottom-start",disabled:E,withRoles:!1},o),_.createElement(K.Target,null,_.createElement(se,R(S(S({"data-dates-input":!0,"data-read-only":E||void 0,autoComplete:"off",ref:s,value:we,onChange:Ne,onBlur:Ae,onFocus:qe,onClick:Te,readOnly:E,rightSection:Ee},n),De),{__staticSelector:"DateInput"}))),_.createElement(K.Dropdown,{onMouseDown:p=>p.preventDefault(),"data-dates-dropdown":!0},_.createElement(Ue,R(S({__staticSelector:"DateInput"},ye),{classNames:be,styles:fe,unstyled:Z,__preventFocus:!0,minDate:j,maxDate:P,locale:t,getDayProps:Be,size:n.size,date:Ce,onDateChange:G}))))),_.createElement(ze,{name:he,form:ge,value:y,type:"default"}))});me.displayName="@mantine/dates/DateInput";function Ea(r){return Ke({tag:"svg",attr:{viewBox:"0 0 512 512"},child:[{tag:"path",attr:{d:"M332.64 64.58C313.18 43.57 286 32 256 32c-30.16 0-57.43 11.5-76.8 32.38-19.58 21.11-29.12 49.8-26.88 80.78C156.76 206.28 203.27 256 256 256s99.16-49.71 103.67-110.82c2.27-30.7-7.33-59.33-27.03-80.6zM432 480H80a31 31 0 01-24.2-11.13c-6.5-7.77-9.12-18.38-7.18-29.11C57.06 392.94 83.4 353.61 124.8 326c36.78-24.51 83.37-38 131.2-38s94.42 13.5 131.2 38c41.4 27.6 67.74 66.93 76.18 113.75 1.94 10.73-.68 21.34-7.18 29.11A31 31 0 01432 480z"}}]})(r)}const H=({onChange:r,onReject:s,onDrop:a,accept:n,multiple:i,value:l,label:x})=>{const g=J();return e.jsxs(M,{multiple:i,onChange:r,onReject:s,maxSize:3*1024**2,accept:n,onDrop:a,children:[e.jsxs(ie,{position:"center",spacing:"xl",style:{minHeight:Ve(220),pointerEvents:"none",position:"relative",overflow:"hidden"},children:[e.jsx(M.Accept,{children:e.jsx(We,{size:"3.2rem",color:g.colors[g.primaryColor][g.colorScheme==="dark"?4:6]})}),e.jsx(M.Reject,{children:e.jsx(ua,{size:"3.2rem",color:g.colors.red[g.colorScheme==="dark"?4:6]})}),e.jsx(M.Idle,{children:e.jsx(Ye,{size:"3.2rem"})}),e.jsx($e,{sx:{zIndex:9,color:l&&!i?"white":"black"},children:x}),!i&&l&&(l==null?void 0:l.length)>0&&l.map((c,b)=>{const o=URL.createObjectURL(c);return e.jsx(te,{src:o,sx:{position:"absolute",filter:"brightness(50%)",top:0},imageProps:{onLoad:()=>URL.revokeObjectURL(o)}},b)})]}),e.jsx(He,{cols:4,mt:5,breakpoints:[{maxWidth:"sm",cols:1}],children:i&&l&&n===ca&&l.map((c,b)=>{const o=URL.createObjectURL(c);return e.jsx(te,{src:o,w:20,imageProps:{onLoad:()=>URL.revokeObjectURL(o)}},b)})})]})},La=()=>{var n,i,l,x,g,c,b,o,m,t,d,h;J();const{register:r,control:s,formState:{errors:a}}=de();return e.jsxs(A,{spacing:10,children:[e.jsx(B,{render:({field:{onChange:j,value:P}})=>e.jsx(H,{children:e.jsx("div",{}),label:"Upload pas Photo 3x4, Max : 5MB (DIBUTUHKAN)",onDrop:I=>{j(I)},value:P,multiple:!1,onChange:I=>{var C;return j(((C=I.target.files)==null?void 0:C[0])??null)},onReject:I=>{I[0].errors[0].code=="file-too-large"&&O.error("Size gambar terlalu besar dari 5MB")}}),name:"profile_picture",control:s,rules:{required:{value:!0,message:"Dibutuhkan"}}}),e.jsxs(u,{children:[e.jsx(u.Col,{lg:6,sm:12,children:e.jsx(f,{description:"Masukkan NISN",label:"NISN",withAsterisk:!1,placeholder:"Nomor NISN",error:a.nisn&&e.jsx("div",{children:(n=a.nisn)==null?void 0:n.message}),required:!0,...r("nisn",{required:!1})})}),e.jsx(u.Col,{lg:6,sm:12,children:e.jsx(f,{description:"Nomor Whatsapp",label:"Nomor WhatsApp",placeholder:"Nomor Whatsapp",error:a.phone&&e.jsx("div",{children:(i=a.phone)==null?void 0:i.message}),required:!0,...r("phone",{required:{value:!0,message:"Dibutuhkan"}})})}),e.jsx(u.Col,{lg:6,sm:12,children:e.jsx(f,{description:"Nama Lengkap",label:"Nama Lengkap",placeholder:"Nama Lengkap",error:a.name&&e.jsx("div",{children:(l=a.name)==null?void 0:l.message}),required:!0,...r("name",{required:{value:!0,message:"Dibutuhkan"}})})}),e.jsx(u.Col,{lg:6,sm:12,children:e.jsx(f,{description:"Nama Panggilan",label:"Nama Panggilan",withAsterisk:!1,placeholder:"Nama Panggilan",error:a.surname&&e.jsx("div",{children:(x=a.surname)==null?void 0:x.message}),required:!0,...r("surname",{required:!1})})}),e.jsx(u.Col,{lg:6,sm:12,children:e.jsx(pa,{name:"gender",control:s,label:"Gender",rules:{required:{value:!0,message:"Dibutuhkan"}},description:"Pilih salah satu",children:e.jsxs(ie,{mt:"xs",children:[e.jsx(le,{value:"L",label:"Laki laki"}),e.jsx(le,{value:"P",label:"Perempuan"})]})})}),e.jsx(u.Col,{lg:6,sm:12,children:e.jsx(B,{render:({field:{onChange:j,value:P}})=>e.jsx(ma,{type:"RELIGION",readonly:!1,label:"Agama",onChange:j,value:P,searchable:!1}),name:"religion",control:s,rules:{required:{value:!0,message:"Dibutuhkan"}}})}),e.jsx(u.Col,{lg:6,sm:12,children:e.jsx(f,{label:"Tempat Lahir",placeholder:"Tempat Lahir",error:a.birth_place&&e.jsx("div",{children:(g=a.birth_place)==null?void 0:g.message}),required:!0,...r("birth_place",{required:{value:!0,message:"Dibutuhkan"}})})}),e.jsx(u.Col,{lg:6,sm:12,children:e.jsx(B,{render:({field:{onChange:j,value:P}})=>e.jsx(me,{value:P,onChange:j,label:"Tanggal Lahir",placeholder:"Tanggal Lahir"}),name:"birth_date",control:s,rules:{required:{value:!0,message:"Dibutuhkan"}}})}),e.jsx(u.Col,{lg:6,sm:12,children:e.jsx($,{label:"Alamat",autosize:!0,minRows:3,placeholder:"Alamat",error:a.address&&e.jsx("div",{children:(c=a.address)==null?void 0:c.message}),required:!0,...r("address",{required:{value:!0,message:"Dibutuhkan"}})})}),e.jsx(u.Col,{lg:6,sm:12,children:e.jsx(f,{label:"Provinsi",placeholder:"Provinsi",error:a.province&&e.jsx("div",{children:(b=a.province)==null?void 0:b.message}),required:!0,...r("province",{required:{value:!0,message:"Dibutuhkan"}})})}),e.jsx(u.Col,{lg:6,sm:12,children:e.jsx(f,{label:"Kota/Kabupaten",placeholder:"Kota/Kabupaten",error:a.city&&e.jsx("div",{children:(o=a.city)==null?void 0:o.message}),required:!0,...r("city",{required:{value:!0,message:"Dibutuhkan"}})})}),e.jsx(u.Col,{lg:6,sm:12,children:e.jsx(f,{label:"Kecamatan",placeholder:"Kecamatan",error:a.district&&e.jsx("div",{children:(m=a.district)==null?void 0:m.message}),required:!0,...r("district",{required:{value:!0,message:"Dibutuhkan"}})})}),e.jsx(u.Col,{lg:6,sm:12,children:e.jsx(f,{label:"Kelurahan",placeholder:"Kelurahan",error:a.sub_district&&e.jsx("div",{children:(t=a.sub_district)==null?void 0:t.message}),required:!0,...r("sub_district",{required:{value:!0,message:"Dibutuhkan"}})})}),e.jsx(u.Col,{lg:6,sm:12,children:e.jsx(f,{label:"Kode Pos",placeholder:"Kode Pos",withAsterisk:!1,error:a.postal_code&&e.jsx("div",{children:(d=a.postal_code)==null?void 0:d.message}),required:!0,...r("postal_code",{required:!1})})}),e.jsx(u.Col,{lg:6,sm:12,children:e.jsx(f,{label:"Asal Sekolah",placeholder:"Asal Sekolah",error:a.school_origin&&e.jsx("div",{children:(h=a.school_origin)==null?void 0:h.message}),required:!0,...r("school_origin",{required:{value:!0,message:"Dibutuhkan"}})})})]})]})},Fa=()=>{var n,i,l,x,g,c,b,o;J();const{register:r,control:s,formState:{errors:a}}=de();return e.jsxs(A,{spacing:10,children:[e.jsxs(u,{children:[e.jsx(u.Col,{lg:6,sm:12,children:e.jsx(f,{description:"Nama Ayah",label:"Nama Ayah",placeholder:"Nama Ayah",error:a.dad_name&&e.jsx("div",{children:(n=a.dad_name)==null?void 0:n.message}),required:!0,...r("dad_name",{required:{value:!0,message:"Dibutuhkan"}})})}),e.jsx(u.Col,{lg:6,sm:12,children:e.jsx(f,{description:"Nama Ibu",label:"Nama Ibu",placeholder:"Nama Ibu",error:a.mother_name&&e.jsx("div",{children:(i=a.mother_name)==null?void 0:i.message}),required:!0,...r("mother_name",{required:{value:!0,message:"Dibutuhkan"}})})}),e.jsx(u.Col,{lg:6,sm:12,children:e.jsx(f,{description:"Pekerjaan Ayah",label:"Pekerjaan Ayah",withAsterisk:!1,placeholder:"Pekerjaan Ayah",error:a.dad_job&&e.jsx("div",{children:(l=a.dad_job)==null?void 0:l.message}),required:!0,...r("dad_job",{required:!1})})}),e.jsx(u.Col,{lg:6,sm:12,children:e.jsx(f,{description:"Pekerjaan Ibu",label:"Pekerjaan Ibu",placeholder:"Pekerjaan Ibu",withAsterisk:!1,error:a.mother_job&&e.jsx("div",{children:(x=a.mother_job)==null?void 0:x.message}),required:!0,...r("mother_job",{required:!1})})}),e.jsx(u.Col,{lg:6,sm:12,children:e.jsx(f,{description:"Nomor Telepon Ayah",label:"Nomor Telepon Ayah",placeholder:"Nomor Telepon Ayah",error:a.dad_phone&&e.jsx("div",{children:(g=a.dad_phone)==null?void 0:g.message}),required:!0,...r("dad_phone",{required:{value:!0,message:"Dibutuhkan"}})})}),e.jsx(u.Col,{lg:6,sm:12,children:e.jsx(f,{description:"Nomor Telepon Ibu",label:"Nomor Telepon Ibu",placeholder:"Nomor Telepon Ibu",error:a.mother_phone&&e.jsx("div",{children:(c=a.mother_phone)==null?void 0:c.message}),required:!0,...r("mother_phone",{required:{value:!0,message:"Dibutuhkan"}})})}),e.jsx(u.Col,{lg:6,sm:12,children:e.jsx($,{label:"Alamat Ayah",autosize:!0,minRows:3,placeholder:"Alamat Ayah",error:a.dad_address&&e.jsx("div",{children:(b=a.dad_address)==null?void 0:b.message}),withAsterisk:!1,required:!0,...r("dad_address",{required:!1})})}),e.jsx(u.Col,{lg:6,sm:12,children:e.jsx($,{label:"Alamat Ibu",autosize:!0,minRows:3,placeholder:"Alamat Ibu",error:a.mother_address&&e.jsx("div",{children:(o=a.mother_address)==null?void 0:o.message}),withAsterisk:!1,required:!0,...r("mother_address",{required:!1})})})]}),e.jsx(B,{render:({field:{onChange:m,value:t}})=>e.jsx(H,{children:e.jsx("div",{}),label:"Upload kartu Keluarga 3x4, Max : 5MB ( WAJIB )",onDrop:d=>{m(d)},value:t,multiple:!1,onChange:d=>{var h;return m(((h=d.target.files)==null?void 0:h[0])??null)},onReject:d=>{d[0].errors[0].code=="file-too-large"&&O.error("Size gambar terlalu besar dari 5MB")}}),name:"family_card",control:s,rules:{required:{value:!0,message:"Dibutuhkan"}}}),e.jsx(B,{render:({field:{onChange:m,value:t}})=>e.jsx(H,{children:e.jsx("div",{}),label:" Upload akta kelahiran 3x4, Max : 5MB ( WAJIB )",onDrop:d=>{m(d)},value:t,multiple:!1,onChange:d=>{var h;return m(((h=d.target.files)==null?void 0:h[0])??null)},onReject:d=>{d[0].errors[0].code=="file-too-large"&&O.error("Size gambar terlalu besar dari 5MB")}}),rules:{required:{value:!0,message:"Dibutuhkan"}},name:"birth_card",control:s})]})},Ma=async r=>(await Je.put("/v1/student/update-bio",r)).data,Ra=r=>fetch(r).then(s=>s.blob()).then(s=>new Promise((a,n)=>{const i=new FileReader;i.onloadend=()=>a(i.result),i.onerror=n,i.readAsDataURL(s)})),Y=async r=>{const s=await Ra(`${{}.VITE_BASE_BACKEND_URL}uploads/${r}`);return[Oa(s,r)]},Oa=(r,s)=>{let a=r.split(","),n=a[0].match(/:(.*?);/)[1],i=atob(a[1]),l=i.length,x=new Uint8Array(l);for(;l--;)x[l]=i.charCodeAt(l);return new File([x],s,{type:n})},Ua=({type:r="PENGEMBALIAN"})=>{var o,m;const s=ha({step:3,stagingId:null}),[a,n]=v.useState(null),i=Qe(),l=Xe({mutationFn:Ma}),x=t=>{const d=new FormData;for(const[h,j]of Object.entries(t))j!==null&&(h==="profile_picture"||h==="family_card"||h==="birth_card"?d.append(h,j==null?void 0:j[0]):h==="birth_date"?d.append(h,D(j).format("YYYY-MM-DD")):d.append(h,j));l.mutate(d,{onSuccess:()=>{O.success("Sukses update informasi biodata"),i.invalidateQueries({queryKey:["get_last_offset_batch"]})},onError:h=>Sa(h)})},{data:g,isSuccess:c}=ue({queryKey:["student_staging_offset",s.stagingId,r],queryFn:()=>ga(s.stagingId,r),enabled:!!s.stagingId}),b=async()=>{var d;const t=(d=g.data)==null?void 0:d.student;t.profile_picture=await Y(t.profile_picture),t.birth_card=await Y(t.birth_card),t.family_card=await Y(t.family_card),n({...t,birth_date:t.birth_date?D(t.birth_date).toDate():null})};return v.useEffect(()=>{c&&b()},[c,b]),e.jsx(Ia,{id:"form-biodata",initialValues:a,onSubmit:x,children:c&&((o=g.data.current_state)==null?void 0:o.status)==="WAITING_PAYMENT"&&((m=g.data.current_state)==null?void 0:m.type)===r?e.jsx(V,{sx:t=>({backgroundColor:`${t.colorScheme==="dark"?t.colors.dark[7]:t.white}`,padding:"2rem",boxShadow:"0 5px 10px -8px black",borderRadius:"7px"}),children:e.jsxs(A,{children:[e.jsx(W,{children:"Isi Biodata"}),e.jsx("p",{children:"Harap menunggu konfirmasi pembayaran terlebih dahulu sebelum isi biodata"})]})}):e.jsxs(e.Fragment,{children:[e.jsx(V,{sx:t=>({backgroundColor:`${t.colorScheme==="dark"?t.colors.dark[7]:t.white}`,padding:"2rem",boxShadow:"0 5px 10px -8px black",borderRadius:"7px"}),children:e.jsxs(A,{children:[e.jsx(W,{children:"Isi Biodata"}),e.jsx(La,{})]})}),e.jsx(V,{sx:t=>({backgroundColor:`${t.colorScheme==="dark"?t.colors.dark[7]:t.white}`,marginTop:10,padding:"2rem",boxShadow:"0 5px 10px -8px black",borderRadius:"7px"}),children:e.jsxs(A,{children:[e.jsx(W,{children:"Informasi Orang Tua"}),e.jsx(Fa,{}),e.jsx(Ze,{type:"submit",variant:"filled",loading:l.isPending,children:"Submit"})]})})]})})};function za(r){return e.jsx(oa,{unstyled:!0,styles:s=>({tab:{...s.fn.focusStyles(),backgroundColor:s.colorScheme==="dark"?s.colors.dark[6]:s.white,color:s.colorScheme==="dark"?"white":s.colors.gray[9],border:"none",boxShadow:"0 10px 20px -10px rgba(0,0,0,0.2)",cursor:"pointer",fontSize:s.fontSizes.sm,borderRadius:"5px","&:disabled":{cursor:"not-allowed",color:s.colorScheme==="dark"?s.colors.gray[4]:s.colors.gray[8],backgroundColor:s.colorScheme==="dark"?s.colors.dark[6]:s.colors.gray[4]},"&[data-active]":{background:"linear-gradient(45deg, #4c6ef5 0%, #15aabf 100%)",borderColor:s.colors.blue[7],color:s.white,boxShadow:"0 10px 20px -10px rgba(0,0,0,0.5)"}},tabsList:{overflowX:"auto"},panel:{}}),...r})}const ne=[{index:1,label:"Pilih Jalur PPDB",icon:na,content:e.jsx(_a,{type:"PENGEMBALIAN"})},{index:2,label:"Transaksi Pengembalian",icon:va,content:e.jsx(ka,{type:"PENGEMBALIAN"})},{index:3,label:"Isi Biodata",icon:Ea,content:e.jsx(Ua,{type:"PENGEMBALIAN"})},{index:4,label:"Pilih Jurusan",icon:ia,content:e.jsx(Pa,{type:"PENGEMBALIAN"})},{index:5,label:"Cetak Kartu Peserta",icon:da,content:e.jsx(ya,{type:"PENGEMBALIAN"})}],Ha=()=>{var b;const[r,s]=v.useState({step:1,stagingId:null}),{data:a,isLoading:n,isSuccess:i}=ue({queryKey:["get_last_offset_batch"],queryFn:()=>Da("PENGEMBALIAN"),staleTime:0,notifyOnChangeProps:"all"}),l=xa(r),x=ea(),g=aa();v.useEffect(()=>{s(l==null?void 0:l.initialValues)},[l]),v.useEffect(()=>{if(i){const o=a.data.filter(m=>m.is_done===1);if(o.length>0)if(a.data[a.data.length-1].index!==o[o.length-1].index){const m=o[o.length-1].index+1;c(m.toString())}else c(o[o.length-1].index.toString());else c("1")}},[a,i]);const c=o=>{const m={step:+o,stagingId:a.data.find(t=>t.index===+o).id};g(`${x.pathname}?${ba(m)}`)};return e.jsxs(ra,{title:"Pengembalian",children:[e.jsx(sa,{label:"Pengembalian"}),e.jsx(A,{className:"style-box max-w-[70rem] mx-auto",children:e.jsxs(za,{value:`${r.step}`,onTabChange:c,children:[n&&e.jsx(fa,{width:"100%"}),i&&e.jsx(ja,{activeTabIndex:+r.step,card:a.data.map((o,m)=>{var t;return{label:o.name,index:o.index,icon:(t=ne[m])==null?void 0:t.icon,is_done:o.is_done===1}})}),e.jsx(ta,{my:20}),(b=ne.find(o=>o.index===r.step))==null?void 0:b.content]})}),e.jsx(la,{position:"top-center",reverseOrder:!1})]})};export{Ha as default};